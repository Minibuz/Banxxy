package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.account.AccountDetailedDto;
import fr.esipe.banxxy.dto.account.AccountDto;

import java.util.Optional;

public interface AccountService {
    Optional<AccountDto> getAccountDetails(Integer accountId, Integer userId);

    boolean deleteAccount(Integer userId, Integer accountId);

    Optional<AccountDetailedDto> createAccount(AccountDetailedDto accountDto);
}
