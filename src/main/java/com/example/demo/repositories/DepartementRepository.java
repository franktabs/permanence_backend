package com.example.demo.repositories;

import com.example.demo.entities.Departement;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DepartementRepository extends ModelRepository<Departement, Long> {
    Departement findByOrganizationId(Long organizationId);

    @Transactional
    @Modifying
    @Query("delete from Departement d where d.id = ?1")
    int deleteDepartement(Long id);

}
