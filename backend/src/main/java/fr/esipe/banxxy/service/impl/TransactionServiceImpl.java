package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.TransactionEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.TransactionDto;
import fr.esipe.banxxy.repository.*;
import fr.esipe.banxxy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final UserRepository userRepository;
    private final AdvisorRepository advisorRepository;
    private final AccountRepository accountRepository;
    private  final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(UserRepository userRepository,
                                  AdvisorRepository advisorRepository,
                                  AccountRepository accountRepository,
                                  TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.advisorRepository = advisorRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }
    private boolean isAdvisor(UserEntity user) {
        return user instanceof AdvisorEntity;
    }

    @Override
    public List<TransactionDto> getTransactionList(Integer accountId, Integer userId) {
        var user = userRepository.findById(userId.longValue()).orElseThrow();
        var account = accountRepository.findById(accountId.longValue()).orElseThrow();
        var transactions = user.getTransactions().stream().filter(transaction -> transaction.getAccountFrom().equals(account) || transaction.getAccountTo().equals(account)).toList();

        var transactionDtoList = new ArrayList<TransactionDto>();
        transactions.forEach(transaction ->
                transactionDtoList.add(
                        new TransactionDto(
                                transaction.getAmount(),
                                transaction.getAuthor().getUsername(),
                                transaction.getAccountFrom().getId().intValue(),
                                transaction.getAccountTo().getId().intValue(),
                                transaction.getDate().toString()
                        ))
       );
        return transactionDtoList;
    }

    @Override
    public Integer getNbTransactions(Integer userId) {
        var user = userRepository.findById(userId.longValue()).orElseThrow();
        if (!isAdvisor(user))
            return 0;
        var advisor = advisorRepository.findById(userId.longValue());
        if (advisor.isEmpty()) {
            throw new NoSuchElementException("No customer for this id");
        }
        var customers = advisor.get().getCustomers();
        if (customers.isEmpty()) {
            throw new NoSuchElementException("No customer found for this user");
        }
        var nbTransaction = 0L;
        for (var customer : customers) {
            nbTransaction += customer.getAccounts().stream()
                    .map(accountEntity -> (long) accountEntity.getTransactionsFrom().size())
                    .reduce(0L, Long::sum);
        }
        return (int) nbTransaction;
    }

    @Override
    public Boolean saveTransaction(TransactionDto transactionDto) {
        var date = Date.valueOf(transactionDto.getDate());
        var amount = transactionDto.getAmount();
        var account_fromId = transactionDto.getAccount_from();
        var account_toId = transactionDto.getAccount_to();
        var authorId = transactionDto.getAuthorId();

        var user = userRepository.findById(authorId.longValue()).orElseThrow();
        var accountFrom = accountRepository.findById(account_fromId.longValue()).orElseThrow();
        var accountTo  = accountRepository.findById(account_toId.longValue()).orElseThrow();

        if (accountFrom.getBalance().min(BigInteger.valueOf(amount)).compareTo(BigInteger.ZERO) < 0){
            return false;
        }
        var transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setAccountFrom(accountFrom);
        transactionEntity.setAccountTo(accountTo);
        transactionEntity.setDate(date);
        transactionEntity.setAuthor(user);

        accountFrom.setBalance(accountFrom.getBalance().min(BigInteger.valueOf(amount)));
        accountTo.setBalance(accountTo.getBalance().add(BigInteger.valueOf(amount)));

        //save entity in Data Base
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
        transactionRepository.save(transactionEntity);

        return true;

    }
}
