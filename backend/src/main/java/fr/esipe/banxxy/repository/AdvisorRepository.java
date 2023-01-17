package fr.esipe.banxxy.repository;

import fr.esipe.banxxy.dao.AdvisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends JpaRepository<AdvisorEntity, Long> {
}
