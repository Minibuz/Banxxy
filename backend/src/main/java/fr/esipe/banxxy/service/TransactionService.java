package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getTransactionList(Integer accountId, Integer offset, Integer userId);

    Integer getNbTransactions(Integer userId);


    TransactionDto getTransactionValidation(Integer amount, Integer authorId, Integer account_fromId, Integer account_toId);
}
