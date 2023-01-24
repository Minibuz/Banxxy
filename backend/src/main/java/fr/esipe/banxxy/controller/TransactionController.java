package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.transaction.TransactionDto;
import fr.esipe.banxxy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/apit/transactions/{accountId}/{userId}")
    public ResponseEntity<List<TransactionDto>> getTransaction(@PathVariable Long accountId,
                                                              @PathVariable Long userId) {
        var transactions = transactionService.getTransactionList(accountId, userId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/api/transaction/number{userId}")
    public ResponseEntity<Integer> getNbTransaction(@PathVariable Long userId){
        var nbTransactions = transactionService.getNbTransactions(userId);
        return new ResponseEntity<>(nbTransactions,HttpStatus.OK);
    }

    @PostMapping("/api/transaction/create")
    public ResponseEntity<Boolean> createTransaction(@RequestBody TransactionDto transactionDto) {
        var isSaveTransaction = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(isSaveTransaction, HttpStatus.OK);
    }
}
