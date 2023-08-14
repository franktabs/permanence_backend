package com.example.demo.repositories;

import com.example.demo.entities.Month;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthRepository extends JpaRepository<Month, Long> {
}
