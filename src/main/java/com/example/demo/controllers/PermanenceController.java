package com.example.demo.controllers;

import com.example.demo.dto.*;
import com.example.demo.entities.*;
import com.example.demo.services.MonthService;
import com.example.demo.services.PermanenceService;
import com.example.demo.utils.StringExtract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static com.example.demo.controllers.AbsenceController.convertAbsenceToDto;
import static com.example.demo.controllers.RemplacementController.convertRemplacementToDto;
@RestController
@CrossOrigin
@RequestMapping("/permanence")
public class PermanenceController {

    @Autowired
    PermanenceService permanenceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PermanenceDto>> allPermanence() {
        List<Permanence> permanences = permanenceService.getAllPermanence();
        List<PermanenceDto> permanenceDtos = new ArrayList<>();
        for (Permanence permanence : permanences) {
            permanenceDtos.add(convertPermanenceToDto(permanence, 1, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(permanenceDtos);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermanenceDto> getOnePermanence(@PathVariable Long id){
        Permanence permanence = permanenceService.getPermanenceById(id);
        if(permanence==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertPermanenceToDto(permanence, 1, 1, 1));
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody PermanenceDto permanenceDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Permanence permanence = convertDtoToPermanence(permanenceDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertPermanenceToDto(permanenceService.create(permanence), 1, 1, 1));

        } catch (DataIntegrityViolationException e) {
            Map<String, String> message = StringExtract.keyValueError(e.getMostSpecificCause().getMessage());
            System.out.println("\n\nerreur ici" + message + "\n\n");
            if (message.isEmpty()) {
                message.put("errors", e.getMostSpecificCause().getMessage());
            }
            return ResponseEntity.badRequest().body(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur au niveau du serveur c'est produit");
        }
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
                    personnelNuitDtos.add(PersonnelNuitController.convertPersonnelNuitToDto(personnelNuit, 1, depthPersonnelNuit - 1));
                }
            }

            permanenceDto.setPersonnels_nuit(personnelNuitDtos);
        }
        if (depthPersonnelJour > 0) {
            Set<PersonnelJourDto> personnelJourDtos = new HashSet<>();
            if (permanence.getPersonnelJours() != null) {
                for (PersonnelJour personnelJour : permanence.getPersonnelJours()) {
                    personnelJourDtos.add(PersonnelJourController.convertPersonnelJourToDto(personnelJour,1,  depthPersonnelJour - 1));
                }
            }
            permanenceDto.setPersonnels_jour(personnelJourDtos);
        }

        if(depthMonth>0){
            if(permanence.getMonth()!=null){
                permanenceDto.setMonth(MonthController.convertMonthToDto(permanence.getMonth(), depthMonth-1, 1, 1));
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
