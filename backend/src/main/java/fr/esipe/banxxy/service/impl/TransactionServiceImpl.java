package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.UserEntity;
import fr.esipe.banxxy.dto.TransactionDto;
import fr.esipe.banxxy.repository.AccountRepository;
import fr.esipe.banxxy.repository.AdvisorRepository;
import fr.esipe.banxxy.repository.CustomerRepository;
import fr.esipe.banxxy.repository.UserRepository;
import fr.esipe.banxxy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final UserRepository userRepository;
    private final AdvisorRepository advisorRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionServiceImpl(UserRepository userRepository,
                                  AdvisorRepository advisorRepository,
                                  CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.advisorRepository = advisorRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }
    private boolean isAdvisor(UserEntity user) {
        return user instanceof AdvisorEntity;
    }

    @Override
    public List<TransactionDto> getTransactionList(Integer accountId, Integer offset, Integer userId) {
        var user = userRepository.findById(userId);
        var account = accountRepository.findById(accountId);
        var transactions = user.get().getTransactions().stream().filter(transaction -> transaction.getAccountFrom().equals(account) || transaction.getAccountTo().equals(account)).toList();

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
        return null;
    }
}
