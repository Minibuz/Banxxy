package fr.esipe.banxxy.dto;

public class AccountDto {

    private String owner;
    private String advisor;
    private Long balance;

    public AccountDto(String ownerFirstName, String ownerLastName,
                      String advisorFirstName, String advisorLastName,
                      Long balance) {
        this.owner = ownerFirstName + " " + ownerLastName;
        this.advisor = advisorFirstName + " " + advisorLastName;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public String getAdvisor() {
        return advisor;
    }

    public Long getBalance() {
        return balance;
    }
}