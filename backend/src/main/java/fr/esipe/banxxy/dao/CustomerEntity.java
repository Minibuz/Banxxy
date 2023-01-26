package fr.esipe.banxxy.dao;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
@DiscriminatorValue(value = "customer")
public class CustomerEntity extends UserEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advisor")
    private AdvisorEntity advisor;
    @ManyToOne
    @JoinColumn(name = "parent")
    private CustomerEntity parent;
    @OneToMany(mappedBy = "customer")
    private Set<AccountEntity> accounts;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<CustomerEntity> childrens;

    /**
     * Returns this customer's accounts.
     *
     * @return accounts This customer's accounts. (Set<AccountEntity>)
     */
    public Set<AccountEntity> getAccounts() {
        return accounts;
    }

    /**
     * Sets this customer's accounts from a AccountEntity set.
     *
     * @param accounts Sets this customer's accounts. (Set<AccountEntity>)
     */
    public void setAccounts(Set<AccountEntity> accounts) {
        this.accounts = accounts;
    }

    /**
     * Returns this customer's advisor.
     *
     * @return advisor This customer's advisor. (AdvisorEntity)
     */
    public AdvisorEntity getAdvisor() {
        return advisor;
    }

    /**
     * Sets a new advisor for this customer.
     *
     * @param advisor This customer's new advisor. (AdvisorEntity)
     */
    public void setAdvisor(AdvisorEntity advisor) {
        this.advisor = advisor;
    }

    /**
     * Returns this customer's parent account.
     *
     * @return parent This customer's Parent Account. (CustomerEntity)
     */
    public CustomerEntity getParent() {
        return parent;
    }

    /**
     * Sets a new parent account for this customer.
     *
     * @param parent This customer's new parent account. (CustomerEntity)
     */
    public void setParent(CustomerEntity parent) {
        this.parent = parent;
    }

    /**
     * Returns a list of this customer's children accounts.
     *
     * @return children A list of this customer's children accounts. (List<CustomerEntity>)
     * */
    public List<CustomerEntity> getChildrens() {
        return childrens;
    }

    /**
     * Sets a new children account(s) list for this customer.
     *
     * @param childrens A new children account(s) list for this customer (List<CustomerEntity>)
     */
    public void setChildrens(List<CustomerEntity> childrens) {
        this.childrens = childrens;
    }
}