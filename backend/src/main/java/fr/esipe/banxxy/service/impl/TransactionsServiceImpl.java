package fr.esipe.banxxy.service.impl;

import fr.esipe.banxxy.dto.transaction.TransactionDto;
import fr.esipe.banxxy.repository.AccountRepository;
import fr.esipe.banxxy.repository.UserRepository;
import fr.esipe.banxxy.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TransactionsServiceImpl  implements TransactionsService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionsServiceImpl(UserRepository userRepository,
                                  AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
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
