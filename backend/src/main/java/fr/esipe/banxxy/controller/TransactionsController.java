package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.transaction.TransactionDto;
import fr.esipe.banxxy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{accountId}/{userId}")
    public ResponseEntity<List<TransactionDto>> getTransaction(@PathVariable Long accountId,
                                                              @PathVariable Long userId) {
        var transactions = transactionService.getTransactionList(accountId, userId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
