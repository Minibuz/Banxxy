package fr.esipe.banxxy.dto.account;

import java.util.Set;

public class AccountsChildrenDto {

    private String owner;
    private Long id;
    private Set<AccountDto> accounts;

    public AccountsChildrenDto(String ownerFirstName, String ownerLastName,
                               Long id, Set<AccountDto> accounts) {
        this.id = id;
        this.owner = ownerFirstName + " " + ownerLastName;
        this.accounts = accounts;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountDto> accounts) {
        this.accounts = accounts;
    }
}