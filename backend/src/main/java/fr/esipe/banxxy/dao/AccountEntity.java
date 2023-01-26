package fr.esipe.banxxy.dao;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "account", schema = "public", catalog = "compte")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Column(name = "balance", nullable = false)
    private BigInteger balance;
    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountFrom")
    private List<TransactionEntity> transactionsFrom;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountTo")
    private List<TransactionEntity> transactionTo;

    public AccountEntity() {
    }

    /**
     * Returns the account's ID.
     *
     * @return id The account's ID. (Long)
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets a new ID for the account.
     *
     * @param id the new account's ID. (Long)
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the account's owner as a CustomerEntity.
     *
     * @return customer The account's owner. (CustomerEntity)
     */
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * Sets a new owner for the account.
     *
     * @param customer The new account's owner. (CustomerEntity)
     */
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    /**
     * Returns the account's current balance.
     *
     * @return balance The account's current balance. (BigInteger)
     */
    public BigInteger getBalance() {
        return balance;
    }

    /**
     * Sets a new balance for the account.
     *
     * @param balance A new balance amount for the account. (BigInteger)
     */
    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    /**
     * Returns the account's title.
     *
     * @return title The account's title (String).
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a new title for the account
     *
     * @param title A new title for the account (String)
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns a list filled with the account's latest transactions from this account to another.
     *
     * @return transactionsFrom A List filled with the account's latest transactions from it to another account (List<TransactionEntity>)
     */
    public List<TransactionEntity> getTransactionsFrom() {
        return transactionsFrom;
    }

    /**
     * Sets a new transactions list for the transactions which come from this account to another.
     *
     * @param transactionsFrom A new transactions list. (List<TransactionEntity>)
     */
    public void setTransactionsFrom(List<TransactionEntity> transactionsFrom) {
        this.transactionsFrom = transactionsFrom;
    }

    /**
     * Returns a list filled with the account's latest transactions coming from another account to this one.
     *
     * @return transactionsFrom A List filled with the account's latest transactions from another account to this one. (List<TransactionEntity>)
     */
    public List<TransactionEntity> getTransactionTo() {
        return transactionTo;
    }

    /**
     * Sets a new transactions list for the transactions which go from another account to this account.
     *
     * @param transactionTo A new transactions list. (List<TransactionEntity>)
     */
    public void setTransactionTo(List<TransactionEntity> transactionTo) {
        this.transactionTo = transactionTo;
    }
}