package fr.esipe.banxxy.repository;

import fr.esipe.banxxy.dao.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {
}
