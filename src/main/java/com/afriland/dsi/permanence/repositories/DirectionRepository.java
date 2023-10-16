package com.afriland.dsi.permanence.repositories;

import com.afriland.dsi.permanence.entities.Departement;
import com.afriland.dsi.permanence.entities.Direction;
import com.afriland.dsi.permanence.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DirectionRepository extends ModelRepository<Direction, Long> {
    Direction findByOrganizationId(Long organizationId);

    @Override
    @Transactional
    @Modifying
    @Query("delete from Direction d where d.id = ?1")
    int deleteModel(Long id);


    @Query("select d from Direction d where d.organizationId = ( select MIN(d2.organizationId) from Direction d2 )")
    Direction findMinOrganizationId();


}
