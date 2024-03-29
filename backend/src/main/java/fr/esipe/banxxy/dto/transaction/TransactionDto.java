package fr.esipe.banxxy.dto.transaction;

public class TransactionDto {
    private Integer amount;
    private String author;
    private Long authorId;
    private Long account_from;
    private Long account_to;
    private String date;

    public TransactionDto(Integer amount, String author,
                          Long authorId, Long account_from,
                          Long account_to, String date) {
        this.amount = amount;
        this.author = author;
        this.authorId = authorId;
        this.account_from = account_from;
        this.account_to = account_to;
        this.date = date;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setAccount_from(Long account_from) {
        this.account_from = account_from;
    }

    public void setAccount_to(Long account_to) {
        this.account_to = account_to;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getAuthor() {
        return author;
    }

    public Long getAccount_from() {
        return account_from;
    }

    public Long getAccount_to() {
        return account_to;
    }
    
    public Long getAuthorId(){ return authorId; }

    public String getDate(){ return date; }
}
