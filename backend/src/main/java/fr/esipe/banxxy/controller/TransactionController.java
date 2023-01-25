package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.transaction.TransactionDto;
import fr.esipe.banxxy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Integer> getNbTransaction(@PathVariable Long userId){
        var nbTransactions = transactionService.getNbTransactions(userId);
        return new ResponseEntity<>(nbTransactions,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createTransaction(@RequestBody TransactionDto transactionDto) {
        var isSaveTransaction = transactionService.createTransaction(transactionDto);
        if (isSaveTransaction != null && isSaveTransaction)
            return new ResponseEntity<>(isSaveTransaction, HttpStatus.OK);
        return new ResponseEntity<>(isSaveTransaction,HttpStatus.UNAUTHORIZED);
    }
}
