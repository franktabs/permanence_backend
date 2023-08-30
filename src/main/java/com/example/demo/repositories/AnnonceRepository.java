package com.example.demo.repositories;

import com.example.demo.entities.Annonce;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnonceRepository extends ModelRepository<Annonce, Long> {
}
