package com.example.demo.controllers;

import com.example.demo.dto.MonthDto;
import com.example.demo.dto.PersonnelJourDto;
import com.example.demo.dto.PersonnelNuitDto;
import com.example.demo.entities.Month;
import com.example.demo.entities.PersonnelJour;
import com.example.demo.entities.PersonnelNuit;
import com.example.demo.services.MonthService;
import com.example.demo.services.PersonnelJourService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping(path = "personnel_jour")
public class PersonnelJourController {


    @Autowired
    PersonnelJourService personnelJourService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonnelJourDto>> allPersonnelJour() {
        List<PersonnelJour> planningJours = personnelJourService.getAllPersonnelJour();
        List<PersonnelJourDto> planningJourDtos = new ArrayList<>();
        for (PersonnelJour planningJour : planningJours) {
            planningJourDtos.add(convertPersonnelJourToDto(planningJour, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(planningJourDtos);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonnelJourDto> getOnePersonnelJour(@PathVariable Long id) {
        PersonnelJour planningJour = personnelJourService.getPersonnelJourById(id);
        if (planningJour == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertPersonnelJourToDto(planningJour, 1, 1));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePersonnelJour(@PathVariable Long id) {
        if (personnelJourService.delete(id)) {
            return ResponseEntity.ok().body(Map.of("success", "Operation réussi"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("errors", "echec de l'operation"));
        }
    }



    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody PersonnelJourDto personnelJourDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            PersonnelJour personnelJour = convertDtoToPersonnelJour(personnelJourDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertPersonnelJourToDto(personnelJourService.create(personnelJour), 1, 1));

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


    public static PersonnelJourDto convertPersonnelJourToDto(PersonnelJour personnelJour, int depthPersonnel, int depthPermanence) {
        PersonnelJourDto personnelJourDto = new PersonnelJourDto(
                personnelJour.getId(),
                personnelJour.getResponsable()
        );

        if (depthPersonnel > 0) {
            if (personnelJour.getPersonnel() != null) {
                personnelJourDto.setPersonnel(PersonnelController.convertPersonnelToDto(personnelJour.getPersonnel(), 1, 1, 1, 1, depthPersonnel - 1, 1, 1, 0, 0));
            }
        }

        if (depthPermanence > 0) {
            if (personnelJour.getPermanence() != null) {
                personnelJourDto.setPermanence(PermanenceController.convertPermanenceToDto(personnelJour.getPermanence(), depthPersonnel - 1, 1, 1));
            }
        }

        return personnelJourDto;
    }


    public static PersonnelJour convertDtoToPersonnelJour(PersonnelJourDto personnelJourDto) {
        PersonnelJour personnelJour = new PersonnelJour(
                personnelJourDto.getId(),
                personnelJourDto.getResponsable()
        );

        if (personnelJourDto.getPersonnel() != null) {
            personnelJour.setPersonnel(PersonnelController.convertDtoToPersonnel(personnelJourDto.getPersonnel()));
        }

        if (personnelJourDto.getPermanence() != null) {
            personnelJour.setPermanence(PermanenceController.convertDtoToPermanence(personnelJourDto.getPermanence()));

        }

        return personnelJour;
    }
}
