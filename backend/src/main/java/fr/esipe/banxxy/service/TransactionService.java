package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.transaction.TransactionDto;

import java.util.List;

public interface TransactionService {

    List<TransactionDto> getTransactionList(Long accountId, Long userId);

    Integer getNbTransactions(Long userId);

    Boolean createTransaction(TransactionDto transactionDto);
}
