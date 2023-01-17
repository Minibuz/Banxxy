package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.TransactionDto;
import fr.esipe.banxxy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{accountId}/{userId}")
    public List<TransactionDto> getTransaction(@PathVariable Long accountId,
                                               @PathVariable Long userId) {
        return transactionService.getTransactionList(accountId, userId);
    }

    @GetMapping("/{userId}")
    public Integer getNbTransaction(@PathVariable Long userId){
        return transactionService.getNbTransactions(userId);
    }

    @PostMapping("/create")
    public Boolean saveTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionService.saveTransaction(transactionDto);
    }
}
