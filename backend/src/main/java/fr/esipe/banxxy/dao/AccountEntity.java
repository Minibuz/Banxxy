package fr.esipe.banxxy.dao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account", schema = "public", catalog = "compte")
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountFrom")
    private List<TransactionEntity> transactionsFrom;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountTo")
    private List<TransactionEntity> transactionTo;

    public AccountEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
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