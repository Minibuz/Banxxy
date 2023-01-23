package fr.esipe.banxxy.dto.account;

import java.math.BigInteger;

public class AccountDto {

    private String title;
    private Long id;
    private BigInteger balance;

    public AccountDto(String title, Long id, BigInteger balance) {
        this.title = title;
        this.id = id;
        this.balance = balance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }
}