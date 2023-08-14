package com.example.demo.services;

import com.example.demo.entities.Permanence;
import com.example.demo.entities.Permanence;
import com.example.demo.repositories.PermanenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermanenceService {
    @Autowired
    private PermanenceRepository permanenceRepository;

    public Permanence create(Permanence permanence){
        return permanenceRepository.save(permanence);
    }


    public Permanence update(Permanence permanenceUpdate, Long id){
        Permanence permanence1 = permanenceRepository.findById(id).orElse(null);
        if(permanence1==null) return null;
        if(!permanence1.getId().equals(permanenceUpdate.getId())) return null;
        return permanenceRepository.save(permanenceUpdate);
    }

    public List<Permanence> getAllPermanence(){
        return permanenceRepository.findAll();
    }

    public Permanence getPermanenceById(Long id){
        return permanenceRepository.findById(id).orElse(null);
    }
}
