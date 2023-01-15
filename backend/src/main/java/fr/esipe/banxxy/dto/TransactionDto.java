package fr.esipe.banxxy.dto;

public class TransactionDto {
    private final Integer amount;
    private final String author;
    private final Integer account_from;
    private final Integer account_to;
    private  final String date;

    public TransactionDto(Integer amount, String author, Integer account_from, Integer account_to, String date){
        this.amount = amount;
        this.author = author;
        this.account_from = account_from;
        this.account_to = account_to;
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getAccount_from() {
        return account_from;
    }

    public Integer getAccount_to() {
        return account_to;
    }
}
