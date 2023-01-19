package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.AccountDetailledDto;
import fr.esipe.banxxy.dto.AccountsParentDto;
import fr.esipe.banxxy.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<AccountsParentDto>> getAccounts(@PathVariable Integer userId) {
        var accounts = accountsService.getAccounts(userId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/attached/{userId}")
    public ResponseEntity<List<AccountsParentDto>> getAttachedAccounts(@PathVariable Integer userId) {
        var accounts = accountsService.getAttachedAccounts(userId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<AccountsParentDto>> getAllAccounts(@PathVariable Integer userId) {
        var accounts = accountsService.getAllAccounts(userId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/detailed/{userId}")
    public ResponseEntity<List<AccountDetailledDto>> getDetailedAccounts(@PathVariable Integer userId) {
        var accounts = accountsService.getDetailedAccounts(userId);
        return accounts.isEmpty() ?
                new ResponseEntity<>(accounts, HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
