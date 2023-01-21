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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TransactionEntity> getTransactionsFrom() {
        return transactionsFrom;
    }

    public void setTransactionsFrom(List<TransactionEntity> transactionsFrom) {
        this.transactionsFrom = transactionsFrom;
    }

    public List<TransactionEntity> getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(List<TransactionEntity> transactionTo) {
        this.transactionTo = transactionTo;
    }
}