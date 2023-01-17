package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.TransactionDto;
import fr.esipe.banxxy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<TransactionDto> getTransaction(@PathVariable Integer accountId,
                                               @PathVariable Integer userId) {
        return transactionService.getTransactionList(accountId, userId);
    }

    @GetMapping("/{userId}")
    public Integer getNbTransaction(@PathVariable Integer userId){
        return transactionService.getNbTransactions(userId);
    }

    @GetMapping("/{amount}/{authorId}/{accountFromId}/{accountToId}")
    public TransactionDto getTransaction(@PathVariable Integer amount,
                                         @PathVariable Integer authorId,
                                         @PathVariable Integer accountFromId,
                                         @PathVariable Integer accountToId){
        return transactionService.getTransactionValidation(amount, authorId, accountFromId, accountToId);
    }
}
