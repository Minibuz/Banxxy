package fr.esipe.banxxy.repository;

import fr.esipe.banxxy.dao.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
}
