package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.TransactionEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.TransactionDto;
import fr.esipe.banxxy.repository.*;
import fr.esipe.banxxy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final UserRepository userRepository;
    private final AdvisorRepository advisorRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private  final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(UserRepository userRepository,
                                  AdvisorRepository advisorRepository,
                                  CustomerRepository customerRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.advisorRepository = advisorRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }
    private boolean isAdvisor(UserEntity user) {
        return user instanceof AdvisorEntity;
    }

    @Override
    public List<TransactionDto> getTransactionList(Integer accountId, Integer offset, Integer userId) {
        var user = userRepository.findById(userId).orElseThrow();
        var account = accountRepository.findById(accountId).orElseThrow();
        var transactions = user.getTransactions().stream().filter(transaction -> transaction.getAccountFrom().equals(account) || transaction.getAccountTo().equals(account)).toList();

       var transactionDtoList = new ArrayList<TransactionDto>();
       transactions.forEach(transaction ->{
           transactionDtoList.add(new TransactionDto(transaction.getAmount(),
                   transaction.getAuthor().getUsername(),
                   transaction.getAccountFrom().getId(),
                   transaction.getAccountTo().getId(),
                   transaction.getDate().toString()));
       });
       return transactionDtoList;
    }

    @Override
    public Integer getNbTransactions(Integer userId) {
        var user = userRepository.findById(userId).orElseThrow();
        if (!isAdvisor(user))
            return 0;
        var advisor = advisorRepository.findById(userId);
        var customers = advisor.get().getCustomers();
        if (customers.isEmpty())
            throw new NoSuchElementException("No customer found for this user");
        Long nbTransaction = 0L;
        for (var customer : customers) {
            nbTransaction += customer.getAccounts().stream()
                    .map(accountEntity -> accountEntity.getTransactionsFrom().stream()
                            .count())
                    .reduce(0L, Long::sum);
        }
        return nbTransaction.intValue();
    }

    @Override
    public TransactionDto getTransactionValidation(Integer amount, Integer authorId, Integer account_fromId, Integer account_toId) {
        var date = new Date(System.currentTimeMillis());

        var user = userRepository.findById(authorId).orElseThrow();
        var accountFrom = accountRepository.findById(account_fromId).orElseThrow();
        var accountTo  = accountRepository.findById(account_toId).orElseThrow();

        if (accountFrom.getBalance() - amount < 0L){
            return null;
        }
        var transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setAccountFrom(accountFrom);
        transactionEntity.setAccountTo(accountTo);
        transactionEntity.setDate(date);
        transactionEntity.setAuthor(user);

        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);

        //save entity in Data Base
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
        transactionRepository.save(transactionEntity);

        return new TransactionDto(amount, user.getName(), account_fromId, account_toId, date.toString());

    }
}
