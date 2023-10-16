package com.afriland.dsi.permanence.repositories;

import com.afriland.dsi.permanence.entities.Permanence;
import com.afriland.dsi.permanence.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PermanenceRepository extends ModelRepository<Permanence, Long> {
    List<Permanence> findByPersonnelJours_Personnel_IdOrPersonnelNuits_Personnel_Id(Long id, Long id1);

    @Override
    @Transactional
    @Modifying
    @Query("delete from Permanence p where p.id = ?1")
    int deleteModel(Long id);
}
