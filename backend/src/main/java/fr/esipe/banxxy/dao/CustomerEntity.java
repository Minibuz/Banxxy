package fr.esipe.banxxy.dao;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<CustomerEntity> childrens;

    public AdvisorEntity getAdvisor() {
        return advisor;
    }

    public void setAdvisor(AdvisorEntity advisor) {
        this.advisor = advisor;
    }

    public CustomerEntity getParent() {
        return parent;
    }

    public void setParent(CustomerEntity parent) {
        this.parent = parent;
    }

    public List<CustomerEntity> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<CustomerEntity> childrens) {
        this.childrens = childrens;
    }
}