package com.example.demo.repositories;

import com.example.demo.entities.Parameter;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends ModelRepository<Parameter, Long> {
}
