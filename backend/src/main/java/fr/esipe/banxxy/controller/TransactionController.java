package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.TransactionDto;
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

    @GetMapping("/{accountId}/{us<erId}")
    public ResponseEntity<List<TransactionDto>> getTransaction(@PathVariable Long accountId,
                                                              @PathVariable Long userId) {
        var transactions = transactionService.getTransactionList(accountId, userId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{userId}")

    public ResponseEntity<Integer> getNbTransaction(@PathVariable Long userId){
        var nbTransactions = transactionService.getNbTransactions(userId);
        return new ResponseEntity<>(nbTransactions,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> saveTransaction(@RequestBody TransactionDto transactionDto) {
        var isSaveTransaction = transactionService.saveTransaction(transactionDto);
        return new ResponseEntity<>(isSaveTransaction, HttpStatus.OK);
    }
}
