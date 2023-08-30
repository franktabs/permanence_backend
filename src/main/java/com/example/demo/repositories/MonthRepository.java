package com.example.demo.repositories;

import com.example.demo.entities.Month;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthRepository extends ModelRepository<Month, Long> {
}
