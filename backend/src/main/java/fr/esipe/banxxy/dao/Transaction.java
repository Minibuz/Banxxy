package fr.esipe.banxxy.dao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name = "operation")
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "noCompte", nullable = false)
    @ManyToOne
    private Account account;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Time time;

    @Column(name = "op", length = 1, nullable = false)
    private String operation;

    @Column(name = "valeur", precision=10, scale=2, nullable = false)
    private BigDecimal value;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
