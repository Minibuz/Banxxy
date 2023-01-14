package fr.esipe.banxxy.dao;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "transaction", schema = "public", catalog = "compte")
public class TransactionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "account_from", nullable = false)
    private AccountEntity accountFrom;
    @ManyToOne
    @JoinColumn(name = "account_to", nullable = false)
    private AccountEntity accountTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public AccountEntity getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(AccountEntity accountFrom) {
        this.accountFrom = accountFrom;
    }

    public AccountEntity getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(AccountEntity accountTo) {
        this.accountTo = accountTo;
    }
}
