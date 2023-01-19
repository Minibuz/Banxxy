package fr.esipe.banxxy.dto;

import java.math.BigInteger;

public class AccountDetailledDto {

    private Long id;
    private String title;
    private String owner;
    private String advisor;
    private BigInteger balance;
    private Long id_owner;

    public AccountDetailledDto(Long id, String title,
                               String ownerFirstName, String ownerLatName,
                               String advisorFirstName, String advisorLastName,
                               BigInteger balance, Long id_owner) {
        this.id = id;
        this.title = title;
        this.owner = ownerFirstName + " " + ownerLatName;
        this.advisor = advisorFirstName + " " + advisorLastName;
        this.balance = balance;
        this.id_owner = id_owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public Long getId_owner() {
        return id_owner;
    }

    public void setId_owner(Long id_owner) {
        this.id_owner = id_owner;
    }
}