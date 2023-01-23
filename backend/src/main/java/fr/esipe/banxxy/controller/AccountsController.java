package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.account.AccountDetailedDto;
import fr.esipe.banxxy.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {

    private final AccountsService accountsService;

    @Autowired
    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<AccountDetailedDto>> getAccounts(@PathVariable Integer userId) {
        var accounts = accountsService.getAccounts(userId);
        return accounts.isEmpty() ?
                new ResponseEntity<>(accounts, HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/attached/{userId}")
    public ResponseEntity<List<AccountDetailedDto>> getAttachedAccounts(@PathVariable Integer userId) {
        var accounts = accountsService.getAttachedAccounts(userId);
        return accounts.isEmpty() ?
                new ResponseEntity<>(accounts, HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<AccountDetailedDto>> getAllAccounts(@PathVariable Integer userId) {
        var accounts = accountsService.getAllAccounts(userId);
        return accounts.isEmpty() ?
                new ResponseEntity<>(accounts, HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}