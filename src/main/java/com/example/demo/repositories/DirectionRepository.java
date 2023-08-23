package com.example.demo.repositories;

import com.example.demo.entities.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    Direction findByOrganizationId(Long organizationId);





}
