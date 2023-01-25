package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.transaction.TransactionDto;

public interface TransactionService {
    Integer getNbTransactions(Long userId);

    Boolean createTransaction(TransactionDto transactionDto);
}
