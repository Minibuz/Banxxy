package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.AccountDto;

import java.util.List;

public interface AccountsService {
    List<AccountDto> getAccounts(Integer userId);

    List<AccountDto> getAttachedAccounts(Integer userId);

    List<AccountDto> getAllAccounts(Integer userId);
}
