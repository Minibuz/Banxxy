package fr.esipe.banxxy.controller;

import fr.esipe.banxxy.dto.AccountDto;
import fr.esipe.banxxy.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("{userId}")
    public List<AccountDto> getAccounts(@PathVariable Integer userId) {
        return accountsService.getAccounts(userId);
    }

    @GetMapping("/attached/{userId}")
    public List<AccountDto> getAttachedAccounts(@PathVariable Integer userId) {
        return accountsService.getAttachedAccounts(userId);
    }

    @GetMapping("/all/{userId}")
    public List<AccountDto> getAllAccounts(@PathVariable Integer userId) {
        return accountsService.getAllAccounts(userId);
    }
}
