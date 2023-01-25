package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.CustomerEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.transaction.TransactionDto;
import fr.esipe.banxxy.repository.AccountRepository;
import fr.esipe.banxxy.repository.AdvisorRepository;
import fr.esipe.banxxy.repository.TransactionRepository;
import fr.esipe.banxxy.repository.UserRepository;
import fr.esipe.banxxy.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TransactionsServiceImpl  implements TransactionsService {
    private final UserRepository userRepository;
    private final AdvisorRepository advisorRepository;
    private final AccountRepository accountRepository;
    private  final TransactionRepository transactionRepository;

    @Autowired
    public TransactionsServiceImpl(UserRepository userRepository,
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
}
