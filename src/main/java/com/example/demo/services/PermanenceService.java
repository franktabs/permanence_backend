package com.example.demo.services;

import com.example.demo.entities.Permanence;
import com.example.demo.repositories.PermanenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermanenceService {
    @Autowired
    private PermanenceRepository permanenceRepository;

    public Permanence getPermanenceById(Long id){
        Optional<Permanence> optionalPermanence = permanenceRepository.findById(id);
        return optionalPermanence.orElse(null);
    }
}
