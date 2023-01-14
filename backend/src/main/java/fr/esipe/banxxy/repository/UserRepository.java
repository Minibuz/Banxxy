package fr.esipe.banxxy.repository;

import fr.esipe.banxxy.dao.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
