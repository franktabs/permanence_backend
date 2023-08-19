package com.example.demo.services;

import com.example.demo.controllers.PermanenceController;
import com.example.demo.dto.PermanenceDto;
import com.example.demo.entities.Permanence;
import com.example.demo.entities.Permanence;
import com.example.demo.entities.PersonnelJour;
import com.example.demo.entities.PersonnelNuit;
import com.example.demo.repositories.PermanenceRepository;
import com.example.demo.repositories.PersonnelJourRepository;
import com.example.demo.repositories.PersonnelNuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PermanenceService {
    @Autowired
    private PermanenceRepository permanenceRepository;

    @Autowired PersonnelJourService personnelJourService;

    @Autowired PersonnelNuitService personnelNuitService;

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

    @Transactional
    public Permanence deleteEntirelyPersonnel(Long id){
        Permanence permanence1 = permanenceRepository.findById(id).orElse(null);
        if(permanence1==null) return null;
        Set<PersonnelJour> personnelJours = permanence1.getPersonnelJours();
        Set<PersonnelNuit> personnelNuits = permanence1.getPersonnelNuits();
        if(personnelJours!=null){
            System.out.println("\nSuppression en cours ");
            for(PersonnelJour personnelJour:personnelJours){
            System.out.println("\nSuppression en cours de personnelJour");
                personnelJourService.suppression(personnelJour.getId());
            }
        }
        if(personnelNuits!=null){
            System.out.println("\nSuppression en cours ");

            for(PersonnelNuit personnelNuit:personnelNuits){
                System.out.println("\nSuppression en cours de personnelNuit");

                personnelNuitService.suppression(personnelNuit.getId());
            }
        }
        return permanenceRepository.findById(id).orElse(null);
    }

    public List<PermanenceDto> findPermanenceByPersonnelId(Long id){

        List<Permanence> permanences =  permanenceRepository.findByPersonnelJours_Personnel_IdOrPersonnelNuits_Personnel_Id(id, id);
        List<PermanenceDto> permanenceDtos = new ArrayList<>();
        if(permanences!=null){
            for (Permanence permanence:permanences){
                PermanenceDto permanenceDto = PermanenceController.convertPermanenceToDto(permanence, 1, 1, 0);
                permanenceDtos.add(permanenceDto);
            }
        }
        return permanenceDtos;
    }



/*    public Permanence updateEntirely(Long id, Permanence permanence){
        Permanence permanence1 = permanenceRepository.findById(id).orElse(null);
        if(permanence1==null) return null;
        if(!permanence1.getId().equals(permanence.getId())) return null;
        Set<PersonnelJour> personnelJours = permanence1.getPersonnelJours();
        Set<PersonnelNuit> personnelNuits = permanence1.getPersonnelNuits();
        personnelNuitRepository.deleteAll(personnelNuits);
        personnelJourRepository.deleteAll(personnelJours);
        return permanenceRepository.save(permanence);
    }*/
}
