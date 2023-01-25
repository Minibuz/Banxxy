package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.transaction.TransactionDto;

import java.util.List;

public interface TransactionsService {
    List<TransactionDto> getTransactionList(Long accountId, Long userId);
}
