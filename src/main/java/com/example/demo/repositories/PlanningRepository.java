package com.example.demo.repositories;

import com.example.demo.entities.Planning;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PlanningRepository extends ModelRepository<Planning, Long> {

    @Override
    @Transactional
    @Modifying
    @Query("delete from Planning p where p.id = ?1")
    int deleteModel(Long aLong);
}
