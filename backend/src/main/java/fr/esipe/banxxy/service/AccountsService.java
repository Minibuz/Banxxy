package fr.esipe.banxxy.service;

import fr.esipe.banxxy.dto.AccountDetailledDto;
import fr.esipe.banxxy.dto.AccountsParentDto;

import java.util.List;

public interface AccountsService {
    List<AccountsParentDto> getAccounts(Integer userId);

    List<AccountsParentDto> getAttachedAccounts(Integer userId);

    List<AccountsParentDto> getAllAccounts(Integer userId);

    List<AccountDetailledDto> getDetailedAccounts(Integer userId);
}
