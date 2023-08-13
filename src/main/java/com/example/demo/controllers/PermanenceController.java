package com.example.demo.controllers;

import com.example.demo.dto.*;
import com.example.demo.entities.*;
import com.example.demo.services.PermanenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.controllers.AbsenceController.convertAbsenceToDto;
import static com.example.demo.controllers.RemplacementController.convertRemplacementToDto;
@RestController
@CrossOrigin
@RequestMapping("/permanence")
public class PermanenceController {

    @Autowired
    private PermanenceService permanenceService;

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermanenceDto> getPermanence(@PathVariable Long id) {
        Permanence permanence = permanenceService.getPermanenceById(id);
        if (permanence == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(convertPermanenceToDto(permanence, 1, 1, 1), HttpStatus.OK);
    }

    public static PermanenceDto convertPermanenceToDto(Permanence permanence, int depthPersonnelJour, int depthPersonnelNuit, int depthMonth) {
        PermanenceDto permanenceDto = new PermanenceDto(
                permanence.getId(),
                permanence.getDate(),
                permanence.getType()
        );

        if (depthPersonnelNuit > 0) {
            Set<PersonnelNuitDto> personnelNuitDtos = new HashSet<>();
            if (permanence.getPersonnelNuits() != null) {
                for (PersonnelNuit personnelNuit : permanence.getPersonnelNuits()) {
                    personnelNuitDtos.add(PersonnelNuitController.convertPersonnelNuitToDto(personnelNuit, depthPersonnelNuit - 1, 1));
                }
            }

            permanenceDto.setPersonnels_nuit(personnelNuitDtos);
        }
        if (depthPersonnelJour > 0) {
            Set<PersonnelJourDto> personnelJourDtos = new HashSet<>();
            if (permanence.getPersonnelJours() != null) {
                for (PersonnelJour personnelJour : permanence.getPersonnelJours()) {
                    personnelJourDtos.add(PersonnelJourController.convertPersonnelJourToDto(personnelJour, depthPersonnelNuit - 1, 1));
                }
            }
            permanenceDto.setPersonnels_jour(personnelJourDtos);
        }

        if(depthMonth>0){
            if(permanence.getMonth()!=null){
                permanenceDto.setMonth(MonthController.convertMonthToDto(permanence.getMonth(), depthMonth-1, 1));
            }
        }


        return permanenceDto;
    }

    public static Permanence convertDtoToPermanence(PermanenceDto permanenceDto){
        Permanence permanence = new Permanence(
                permanenceDto.getId(),
                permanenceDto.getDate(),
                permanenceDto.getType()
        );

        Set<PersonnelJour> personnelJours =new HashSet<>();
        if(permanenceDto.getPersonnels_jour()!=null){
            for(PersonnelJourDto personnelJourDto:permanenceDto.getPersonnels_jour()){
                personnelJours.add(PersonnelJourController.convertDtoToPersonnelJour(personnelJourDto));
            }
        }
        permanence.setPersonnelJours(personnelJours);

        Set<PersonnelNuit> personnelNuit =new HashSet<>();
        if(permanenceDto.getPersonnels_nuit()!=null){
            for(PersonnelNuitDto personnelNuitDto:permanenceDto.getPersonnels_nuit()){
                personnelNuit.add(PersonnelNuitController.convertDtoToPersonnelNuit(personnelNuitDto));
            }
        }
        permanence.setPersonnelNuits(personnelNuit);

        if(permanenceDto.getMonth()!=null){
            permanence.setMonth(MonthController.convertDtoToMonth(permanenceDto.getMonth()));
        }

        return permanence;
    }

}
