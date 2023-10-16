package com.afriland.dsi.permanence.services;

import com.afriland.dsi.permanence.controllers.PermanenceController;
import com.afriland.dsi.permanence.dto.PermanenceDto;
import com.afriland.dsi.permanence.entities.*;
import com.afriland.dsi.permanence.entities.Permanence;
import com.afriland.dsi.permanence.repositories.MonthRepository;
import com.afriland.dsi.permanence.repositories.PermanenceRepository;
import com.afriland.dsi.permanence.repositories.PersonnelJourRepository;
import com.afriland.dsi.permanence.repositories.PersonnelNuitRepository;
import com.afriland.dsi.permanence.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PermanenceService extends BaseService<Permanence, PermanenceRepository> {
    @Autowired
    private PermanenceRepository permanenceRepository;

    @Autowired PersonnelJourService personnelJourService;

    @Autowired PersonnelNuitService personnelNuitService;


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
                personnelJourService.delete(personnelJour.getId());
            }
        }
        if(personnelNuits!=null){
            System.out.println("\nSuppression en cours ");

            for(PersonnelNuit personnelNuit:personnelNuits){
                System.out.println("\nSuppression en cours de personnelNuit");

                personnelNuitService.delete(personnelNuit.getId());
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
