package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.transaction.TransactionDto;
import fr.esipe.banxxy.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/{accountId}/{userId}")
    public ResponseEntity<List<TransactionDto>> getTransaction(@PathVariable Long accountId,
                                                              @PathVariable Long userId) {
        var transactions = transactionsService.getTransactionList(accountId, userId);
        if (transactions.isEmpty())
            return new ResponseEntity<>(transactions,HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
