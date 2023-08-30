package com.example.demo.repositories;

import com.example.demo.entities.Absence;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends ModelRepository<Absence, Long> {
}
