package fr.esipe.banxxy.repository;

import fr.esipe.banxxy.dao.AdvisorEntity;
import fr.esipe.banxxy.dao.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvisorRepository extends JpaRepository<AdvisorEntity, Long> {
    @Query("SELECT a FROM AdvisorEntity a JOIN a.customers c WHERE c = :customer")
    AdvisorEntity findByCustomersContaining(@Param("customer") CustomerEntity customer);
}
