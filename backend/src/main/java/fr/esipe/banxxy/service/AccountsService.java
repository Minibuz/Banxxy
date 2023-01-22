package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.AccountDetailledDto;

import java.util.List;

public interface AccountsService {
    List<AccountDetailledDto> getAccounts(Integer userId);

    List<AccountDetailledDto> getAttachedAccounts(Integer userId);

    List<AccountDetailledDto> getAllAccounts(Integer userId);
}
