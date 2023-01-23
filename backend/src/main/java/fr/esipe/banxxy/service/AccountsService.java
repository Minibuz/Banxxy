package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.account.AccountDetailedDto;

import java.util.List;

public interface AccountsService {

    List<AccountDetailedDto> getAccounts(Integer userId);

    List<AccountDetailedDto> getAttachedAccounts(Integer userId);

    List<AccountDetailedDto> getAllAccounts(Integer userId);
}
