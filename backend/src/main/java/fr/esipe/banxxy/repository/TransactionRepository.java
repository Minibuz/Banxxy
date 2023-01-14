package fr.esipe.banxxy.repository;

import fr.esipe.banxxy.dao.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
}
