package fr.esipe.banxxy.dao;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "compte")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "no_compte", length = 4)
    private String noCompte;

    @Column(name = "nom", length = 20, nullable = false)
    private String name;

    @Column(name = "prenom", length = 20, nullable = false)
    private String firstName;

    @Column(name = "solde", precision=10, scale=2, nullable = false)
    private BigDecimal solde;

    public String getNoCompte() {
        return noCompte;
    }
    public void setNoCompte(String noCompte) {
        this.noCompte = noCompte;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public BigDecimal getSolde() {
        return solde;
    }
    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }
}
