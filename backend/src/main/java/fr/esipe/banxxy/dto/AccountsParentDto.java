package fr.esipe.banxxy.dto;

import java.util.Set;

public class AccountsParentDto {

    private String owner;
    private String advisor;
    private Long id;
    private Set<AccountDto> accounts;
    private Set<AccountsChildrenDto> childrens;

    public AccountsParentDto(String ownerFirstName, String ownerLastName,
                               String advisorFirstName, String advisorLastName,
                               Long id, Set<AccountDto> accounts, Set<AccountsChildrenDto> childrens) {
        this.id = id;
        this.owner = ownerFirstName + " " + ownerLastName;
        this.advisor = advisorFirstName + " " + advisorLastName;
        this.accounts = accounts;
        this.childrens = childrens;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
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

    public Set<AccountsChildrenDto> getChildrens() {
        return childrens;
    }

    public void setChildrens(Set<AccountsChildrenDto> childrens) {
        this.childrens = childrens;
    }
}