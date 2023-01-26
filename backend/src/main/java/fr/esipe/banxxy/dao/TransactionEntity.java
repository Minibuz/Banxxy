package fr.esipe.banxxy.dao;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "transaction", schema = "public", catalog = "compte")
public class TransactionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "account_from", nullable = false)
    private AccountEntity accountFrom;
    @ManyToOne
    @JoinColumn(name = "account_to", nullable = false)
    private AccountEntity accountTo;

    /**
     * Returns this transactions's ID.
     *
     * @return id This transaction's ID. (Long)
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets a new ID for this transaction.
     *
     * @param id The transaction's new ID. (Long)
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the date when this transaction happened.
     *
     * @return date The date when this transaction happened. (Date)
     */
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns this transactions's monetary amount.
     *
     * @return id This transaction's monetary amount. (Long)
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets this transaction's monetary amount.
     *
     * @param amount This transaction's new monetary amount. (Integer)
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * Returns this transaction's author.
     *
     * @return author This transaction's author. (UserEntity)
     */
    public UserEntity getAuthor() {
        return author;
    }

    /**
     * Sets a new author for this transaction.
     *
     * @param author This transaction's new author. (UserEntity)
     */
    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    /**
     * Returns the account from which money is sent.
     *
     * @return accountFrom The account from which money is sent. (AccountEntity)
     */
    public AccountEntity getAccountFrom() {
        return accountFrom;
    }

    /**
     * Sets a new account from which money is sent.
     *
     * @param accountFrom The new account from which money is sent. (AccountEntity)
     */
    public void setAccountFrom(AccountEntity accountFrom) {
        this.accountFrom = accountFrom;
    }

    /**
     * Returns the account which gets money.
     *
     * @return accountTo The account which gets money. (AccountEntity)
     */
    public AccountEntity getAccountTo() {
        return accountTo;
    }

    /**
     * Set a new account which gets money.
     *
     * @param accountTo The new account which gets money. (AccountEntity)
     */
    public void setAccountTo(AccountEntity accountTo) {
        this.accountTo = accountTo;
    }
}
