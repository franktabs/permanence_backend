package com.example.demo.services;

import com.example.demo.entities.Permanence;
import com.example.demo.entities.Permanence;
import com.example.demo.entities.PersonnelJour;
import com.example.demo.entities.PersonnelNuit;
import com.example.demo.repositories.PermanenceRepository;
import com.example.demo.repositories.PersonnelJourRepository;
import com.example.demo.repositories.PersonnelNuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PermanenceService {
    @Autowired
    private PermanenceRepository permanenceRepository;
    @Autowired
    private PersonnelNuitRepository personnelNuitRepository;

    @Autowired
    private PersonnelJourRepository personnelJourRepository;

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

    public Permanence updateEntirely(Long id, Permanence permanence){
        Permanence permanence1 = permanenceRepository.findById(id).orElse(null);
        if(permanence1==null) return null;
        if(!permanence1.getId().equals(permanence.getId())) return null;
        Set<PersonnelJour> personnelJours = permanence1.getPersonnelJours();
        Set<PersonnelNuit> personnelNuits = permanence1.getPersonnelNuits();
        personnelNuitRepository.deleteAll(personnelNuits);
        personnelJourRepository.deleteAll(personnelJours);
        return permanenceRepository.save(permanence);
    }
}
