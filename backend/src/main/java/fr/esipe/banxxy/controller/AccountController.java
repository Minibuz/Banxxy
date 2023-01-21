package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.AccountDetailledDto;
import fr.esipe.banxxy.dto.AccountDto;
import fr.esipe.banxxy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}/{userId}")
    public ResponseEntity<AccountDto> getAccountDetails(@PathVariable Integer accountId, @PathVariable Integer userId) {
        var opt = accountService.getAccountDetails(accountId, userId);
        return opt.isEmpty() ?
                new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(opt.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}/{accountId}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable Integer userId, @PathVariable Integer accountId) {
        var result = accountService.deleteAccount(userId, accountId);
        return result ?
                new ResponseEntity<>(true, HttpStatus.OK) :
                new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDetailledDto> createAccount(@RequestBody AccountDetailledDto accountDto) {
        var opt = accountService.createAccount(accountDto);
        return opt.isEmpty() ?
                new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED) :
                new ResponseEntity<>(opt.get(), HttpStatus.CREATED);
    }
}