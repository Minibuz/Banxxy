package fr.esipe.banxxy.repository;

import fr.esipe.banxxy.dao.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,String> {
}
