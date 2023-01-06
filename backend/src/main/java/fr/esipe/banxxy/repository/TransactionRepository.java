package fr.esipe.banxxy.repository;

import fr.esipe.banxxy.dao.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
