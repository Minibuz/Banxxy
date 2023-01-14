package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.AccountDto;
import fr.esipe.banxxy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}/{userId}")
    public AccountDto getAccountDetails(@PathVariable Integer accountId, @PathVariable Integer userId) {
        return accountService.getAccountDetails(accountId, userId);
    }
}
