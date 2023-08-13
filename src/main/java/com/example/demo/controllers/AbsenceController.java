package com.example.demo.controllers;

import com.example.demo.dto.AbsenceDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.dto.PlanningDto;
import com.example.demo.entities.Absence;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.Planning;
import com.example.demo.repositories.AbsenceRepository;
import com.example.demo.services.AbsenceService;
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
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.controllers.DepartementController.convertDepartementToDto;
import static com.example.demo.controllers.PersonnelController.convertDtoToPersonnel;
import static com.example.demo.controllers.PersonnelController.convertPersonnelToDto;

@RestController
@CrossOrigin
@RequestMapping("absence")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> creerOne(@Valid @RequestBody AbsenceDto absenceDto, BindingResult bindingResult) {

        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Absence absence = convertDtoToAbsence(absenceDto);
            System.out.println("\n\n Conversion termninée" + absence.toString() + " \n\n");
            absence = absenceService.createAbsence(absence);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertAbsenceToDto(absence, 1));
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

    @PutMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateOne(@Valid @RequestBody AbsenceDto absenceDto, BindingResult bindingResult, @PathVariable Long id) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Absence absence = convertDtoToAbsence(absenceDto);
            System.out.println("\n\n Conversion termninée" + absence.toString() + " \n\n");
            absence = absenceService.updateAbsence(absence, id);
            if(absence==null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(convertAbsenceToDto(absence, 1));
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
    public static AbsenceDto convertAbsenceToDto(Absence absence, int depthPersonnel){
        AbsenceDto absenceDto = new AbsenceDto(
                absence.getId(),
                absence.getMessage(),
                absence.getMotif(),
                absence.getValidate(),
                absence.getSubmissionDate(),
                absence.getStart(),
                absence.getEnd(),
                absence.getType()
        );
        if (depthPersonnel > 0) {
            if (absence.getPersonnel()!= null) {
                absenceDto.setPersonnel(convertPersonnelToDto(absence.getPersonnel(), 1, depthPersonnel-1,1, 1, 1));
            }
        }
        return absenceDto;
    }

    public static Absence convertDtoToAbsence(AbsenceDto absenceDto){
        Absence absence = new Absence(
                absenceDto.getId(),
                absenceDto.getMessage(),
                absenceDto.getMotif(),
                absenceDto.getValidate(),
                absenceDto.getSubmissionDate(),
                absenceDto.getStart(),
                absenceDto.getEnd(),
                absenceDto.getType_()
        );
        if (absenceDto.getPersonnel()!= null) {
            absence.setPersonnel(convertDtoToPersonnel(absenceDto.getPersonnel()));
        }
        return absence;
    }
}
