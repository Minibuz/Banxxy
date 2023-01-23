package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AdvisorEntity;

import fr.esipe.banxxy.dao.CustomerEntity;
import fr.esipe.banxxy.dao.TransactionEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.transaction.TransactionDto;
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
    private boolean isCustomer(UserEntity user) {
        return user instanceof CustomerEntity;
    }

    @Override
    public List<TransactionDto> getTransactionList(Long accountId, Long userId) {
        var user = userRepository.findById(userId).orElseThrow();
        var account = accountRepository.findById(accountId).orElseThrow();
        var transactions = user.getTransactions().stream().filter(transaction -> transaction.getAccountFrom().equals(account) || transaction.getAccountTo().equals(account)).toList();

       var transactionDtoList = new ArrayList<TransactionDto>();
       transactions.forEach(transaction ->{
           var transactionDto = new TransactionDto(
                   transaction.getAmount(),
                   transaction.getAuthor().getName(),
                   transaction.getAuthor().getId(),
                   transaction.getAccountFrom().getId(),
                   transaction.getAccountTo().getId(),
                   transaction.getDate().toString());
           transactionDtoList.add(transactionDto);

       });
       return transactionDtoList;
    }

    @Override
    public Integer getNbTransactions(Long userId) {
        var user = userRepository.findById(userId).orElseThrow();
        if (!isAdvisor(user))
            return 0;
        if (isCustomer(user))
            return  0;
        var advisor = advisorRepository.findById(userId).orElseThrow();
        var customers = advisor.getCustomers();
        if (customers.isEmpty())
            throw new NoSuchElementException("No customer found for this user");

        int nbTransaction = 0;
        for (var customer : customers) {
            nbTransaction += customer.getAccounts().stream()
                    .map(accountEntity -> accountEntity.getTransactionsFrom().size())
                    .reduce(0, Integer::sum);
        }
        return nbTransaction;
    }

    @Override
    public Boolean saveTransaction(TransactionDto transactionDto) {
        var date = Date.valueOf(transactionDto.getDate());
        var amount = transactionDto.getAmount();
        var account_fromId = transactionDto.getAccount_from();
        var account_toId = transactionDto.getAccount_to();
        var authorId = transactionDto.getAuthorId();

        var user = userRepository.findById(authorId).orElseThrow();
        var accountFrom = accountRepository.findById(account_fromId).orElseThrow();
        var accountTo  = accountRepository.findById(account_toId).orElseThrow();

        if (!isCustomer(user)|| !isAdvisor(user))
            return false;

        if (isCustomer(user) && accountFrom.getCustomer().getId() != user.getId())
            return false;

        if (isAdvisor(user) &&
                (accountFrom.getCustomer().getAdvisor().getId() != user.getId() ||
                 accountTo.getCustomer().getAdvisor().getId() != user.getId()))
            return false;

        if (accountFrom.getBalance().subtract(BigInteger.valueOf(amount)).compareTo(BigInteger.ZERO) < 0){
            return false;
        }
        var transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setAccountFrom(accountFrom);
        transactionEntity.setAccountTo(accountTo);
        transactionEntity.setDate(date);
        transactionEntity.setAuthor(user);

        accountFrom.setBalance(accountFrom.getBalance().subtract(BigInteger.valueOf(amount)));
        accountTo.setBalance(accountTo.getBalance().add(BigInteger.valueOf(amount)));


        //save entity in Data Base
        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
        transactionRepository.save(transactionEntity);

        return true;

    }
}
