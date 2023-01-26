package fr.esipe.banxxy.dao;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "advisor")
@PrimaryKeyJoinColumn(name = "id")
public class AdvisorEntity extends UserEntity {

    @OneToMany(mappedBy = "advisor", fetch = FetchType.EAGER)
    private Set<CustomerEntity> customers;

    /**
     * Returns a set of this advisor's customers.
     *
     * @return customers A set of this advisor's customers. (Set<CustomerEntity>)
     */
    public Set<CustomerEntity> getCustomers() {
        return customers;
    }

    /**
     * Sets a new set of customers for this advisor.
     *
     * @param customers A new set of customers for this advisor. (Set<CustomerEntity>)
     */
    public void setCustomers(Set<CustomerEntity> customers) {
        this.customers = customers;
    }
}
