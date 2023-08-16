package com.example.demo.repositories;

import com.example.demo.entities.RolePersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePersonnelRepository extends JpaRepository<RolePersonnel, Long> {
}
