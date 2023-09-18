package com.example.demo.repositories;

import com.example.demo.entities.Groupe;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupeRepository extends ModelRepository<Groupe, Long> {
}