package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getTransactionList(Integer accountId, Integer userId);

    Integer getNbTransactions(Integer userId);


    Boolean saveTransaction(TransactionDto transactionDto);
}
