package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.AccountDetailledDto;
import fr.esipe.banxxy.dto.AccountDto;

import java.util.Optional;

public interface AccountService {
    Optional<AccountDto> getAccountDetails(Integer accountId, Integer userId);

    boolean deleteAccount(Integer userId, Integer accountId);

    Optional<AccountDetailledDto> createAccount(AccountDetailledDto accountDto);
}
