package fr.esipe.banxxy.dao;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "advisor")
@PrimaryKeyJoinColumn(name = "id")
public class AdvisorEntity extends UserEntity {

    @OneToMany(mappedBy = "advisor", fetch = FetchType.EAGER)
    private Set<CustomerEntity> customers;

    public Set<CustomerEntity> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomerEntity> customers) {
        this.customers = customers;
    }
}
