package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.AccountDto;

public interface AccountService {
    AccountDto getAccountDetails(Integer accountId, Integer userId);
}
