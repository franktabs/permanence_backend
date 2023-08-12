package com.example.demo.controllers;

import com.example.demo.dto.AbsenceDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.entities.Absence;
import com.example.demo.entities.Personnel;
import com.example.demo.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.example.demo.controllers.AbsenceController.convertAbsenceToDto;
import static com.example.demo.controllers.DepartementController.convertDepartementToDto;

@RestController
@RequestMapping(path = "personnel")
public class PersonnelController {

    @Autowired
    PersonnelService personnelService;

    @Autowired
    Validator validator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonnelDto>> allPersonnel(){
        List<Personnel> personnels = personnelService.getAllPerson();
        List<PersonnelDto> personnelDtos = new ArrayList<>();
        for (Personnel personnel:personnels){
            personnelDtos.add(convertPersonnelToDto(personnel, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(personnelDtos);
    }

    @PostMapping()
    public ResponseEntity<?> creer(@RequestBody Personnel personnel){
        Errors errors = new BeanPropertyBindingResult(personnel, "personnel");
        validator.validate(personnel, errors);
        if(errors.hasErrors()){
            Map<String, String> mapErrors = new HashMap<>();
            for(FieldError error: errors.getFieldErrors()){
                mapErrors.put(error.getField(),error.getDefaultMessage());
            }
            return  ResponseEntity.badRequest().body(mapErrors);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(personnelService.creer(personnel));
    }


    public static PersonnelDto convertPersonnelToDto(Personnel personnel, int depthDepartement, int depthAbsence){
        PersonnelDto personnelDto = new PersonnelDto(
                personnel.getId(),
                personnel.getFirstname(),
                personnel.getEmailaddress(),
                personnel.getTelephoneCisco(),
                personnel.getTelephoneMobile(),
                personnel.getUserId(),
                personnel.getSexe(),
                personnel.getFonction(),
                personnel.getService(),
                personnel.getLibAge(),
                personnel.getOrganizationId(),
                personnel.getAgent()
        );
        if (depthDepartement > 0) {
            if (personnel.getDepartement()!= null) {
                personnelDto.setDepartement(convertDepartementToDto(personnel.getDepartement(), 1,depthDepartement - 1));
            }
        }
        if(depthAbsence > 0){

            Set<AbsenceDto> absenceDtos = new HashSet<>();
            for(Absence absence:personnel.getAbsences()){
                absenceDtos.add(convertAbsenceToDto(absence, depthAbsence-1));
            }

            personnelDto.setAbsences(absenceDtos);
        }
        return personnelDto;
    }


}
