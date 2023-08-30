package com.example.demo.repositories;

import com.example.demo.entities.Direction;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DirectionRepository extends ModelRepository<Direction, Long> {
    Direction findByOrganizationId(Long organizationId);

    @Transactional
    @Modifying
    @Query("delete from Direction d where d.id = ?1")
    int deleteDirection(Long id);





}
