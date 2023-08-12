package com.example.demo.controllers;

import com.example.demo.dto.AbsenceDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.dto.RemplacementDto;
import com.example.demo.entities.Absence;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.Remplacement;
import com.example.demo.services.PersonnelService;
import com.example.demo.utils.StringExtract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static com.example.demo.controllers.AbsenceController.convertAbsenceToDto;
import static com.example.demo.controllers.DepartementController.convertDepartementToDto;
import static com.example.demo.controllers.RemplacementController.convertRemplacementToDto;

@RestController
@CrossOrigin
@RequestMapping(path = "personnel")
public class PersonnelController {

    @Autowired
    PersonnelService personnelService;

    @Autowired
    Validator validator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonnelDto>> allPersonnel() {
        List<Personnel> personnels = personnelService.getAllPerson();
        List<PersonnelDto> personnelDtos = new ArrayList<>();
        for (Personnel personnel : personnels) {
            personnelDtos.add(convertPersonnelToDto(personnel, 1, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(personnelDtos);
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody PersonnelDto personnelDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Personnel personnel = convertDtoToPersonnel(personnelDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertPersonnelToDto(personnelService.creer(personnel), 1, 1, 1));

        }
        catch (DataIntegrityViolationException e){
            Map<String, String> message = StringExtract.keyValueError(e.getMostSpecificCause().getMessage());
            System.out.println("\n\nerreur ici"+ message+"\n\n");
            if(message.isEmpty()) {
                message.put("errors", e.getMostSpecificCause().getMessage());
            }
            return ResponseEntity.badRequest().body(message);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur au niveau du serveur c'est produit");
        }
    }


    public static PersonnelDto convertPersonnelToDto(Personnel personnel, int depthDepartement, int depthAbsence, int depthRemplacement) {
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
            if (personnel.getDepartement() != null) {
                personnelDto.setDepartement(convertDepartementToDto(personnel.getDepartement(), 1, depthDepartement - 1));
            }
        }
        if (depthAbsence > 0) {

            Set<AbsenceDto> absenceDtos = new HashSet<>();
            for (Absence absence : personnel.getAbsences()) {
                absenceDtos.add(convertAbsenceToDto(absence, depthAbsence - 1));
            }

            personnelDto.setAbsences(absenceDtos);
        }
        if (depthRemplacement > 0) {

            Set<RemplacementDto> remplacementDtos = new HashSet<>();
            for (Remplacement remplacement : personnel.getRemplacements()) {
                remplacementDtos.add(convertRemplacementToDto(remplacement, depthRemplacement - 1, 1));
            }

            personnelDto.setRemplacements(remplacementDtos);
        }
        return personnelDto;
    }

    public static Personnel convertDtoToPersonnel(PersonnelDto personnelDto) {
        Personnel personnel = new Personnel(
                personnelDto.getId(),
                personnelDto.getFirstname(),
                personnelDto.getEmailaddress(),
                personnelDto.getTelephoneCisco(),
                personnelDto.getTelephoneMobile(),
                personnelDto.getUserId(),
                personnelDto.getSexe(),
                personnelDto.getFonction(),
                personnelDto.getService(),
                personnelDto.getLibAge(),
                personnelDto.getOrganizationId(),
                personnelDto.getAgent()
        );
        if (personnelDto.getDepartement() != null) {
            personnel.setDepartement(DepartementController.convertDtoToDepartement(personnelDto.getDepartement()));
        }
        Set<Absence> absences = new HashSet<>();
        if (personnelDto.getVacancies() != null) {
            for (AbsenceDto absenceDto : personnelDto.getVacancies()) {
                absences.add(AbsenceController.convertDtoToAbsence(absenceDto));
            }
        }
        personnel.setAbsences(absences);

        Set<Remplacement> remplacements = new HashSet<>();

        if (personnelDto.getAbsentList() != null) {

            for (RemplacementDto remplacementDto : personnelDto.getAbsentList()) {
                remplacements.add(RemplacementController.convertDtoToRemplacement(remplacementDto));
            }
        }
        personnel.setRemplacements(remplacements);

        return personnel;
    }

}
