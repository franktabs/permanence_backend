package com.afriland.dsi.permanence.repositories;

import com.afriland.dsi.permanence.entities.Role;
import com.afriland.dsi.permanence.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RoleRepository extends ModelRepository<Role, Long> {

    @Override
    @Transactional
    @Modifying
    @Query("delete from Role p where p.id = ?1")
    int deleteModel(Long id);
}
