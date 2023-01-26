package fr.esipe.banxxy.dto.jms;

import java.util.Date;

public class Log {

    private Date now;

    private Long idAccount;

    private Long idAuthor;

    public Log() {
    }

    public Log(Date now, Long idAccount, Long idAuthor) {
        this.now = now;
        this.idAccount = idAccount;
        this.idAuthor = idAuthor;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public Long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Override
    // [Timestamp horaire demande] [timestamp réception de la demande] [ID Compte] – [ID demandeur]
    public String toString() {
        return "[" + now + "] [" + new Date() + "] [" + idAccount + "] - [" + idAuthor + "]";
    }
}
