package com.example.demo.controllers;

import com.example.demo.dto.MonthDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.dto.PersonnelNuitDto;
import com.example.demo.entities.Month;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.PersonnelNuit;
import com.example.demo.services.MonthService;
import com.example.demo.services.PersonnelNuitService;
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

@RestController
@CrossOrigin
@RequestMapping(path = "personnel_nuit")
public class PersonnelNuitController {

    @Autowired
    PersonnelNuitService personnelNuitService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonnelNuitDto>> allPersonnelNuit() {
        List<PersonnelNuit> personnelNuits = personnelNuitService.getAllPersonnelNuit();
        List<PersonnelNuitDto> personnelNuitDtos = new ArrayList<>();
        for (PersonnelNuit personnelNuit : personnelNuits) {
            personnelNuitDtos.add(convertPersonnelNuitToDto(personnelNuit, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(personnelNuitDtos);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonnelNuitDto> getOnePersonnelNuit(@PathVariable Long id){
        PersonnelNuit personnelNuit = personnelNuitService.getPersonnelNuitById(id);
        if(personnelNuit==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertPersonnelNuitToDto(personnelNuit, 1, 1));
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody PersonnelNuitDto personnelNuitDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            PersonnelNuit personnelNuit = convertDtoToPersonnelNuit(personnelNuitDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertPersonnelNuitToDto(personnelNuitService.create(personnelNuit), 1, 1));

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

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePersonnelNuit(@PathVariable Long id) {
        if (personnelNuitService.delete(id)) {
            return ResponseEntity.ok().body(Map.of("success", "Operation réussi"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("errors", "echec de l'operation"));
        }
    }


    public static PersonnelNuitDto convertPersonnelNuitToDto(PersonnelNuit personnelNuit, int depthPersonnel, int depthPermanence){
        PersonnelNuitDto personnelNuitDto = new PersonnelNuitDto(
                personnelNuit.getId(),
                personnelNuit.getResponsable()
        );

        if(depthPersonnel>0){
            if(personnelNuit.getPersonnel()!=null){
               personnelNuitDto.setPersonnel(PersonnelController.convertPersonnelToDto(personnelNuit.getPersonnel(), 1,1, 1, depthPersonnel-1, 1, 1, 1));
            }
        }

        if(depthPermanence > 0){
            if(personnelNuit.getPermanence()!=null){
                personnelNuitDto.setPermanence(PermanenceController.convertPermanenceToDto(personnelNuit.getPermanence(), 1, depthPermanence-1, 1));
            }
        }

        return personnelNuitDto;
    }

    public static PersonnelNuit convertDtoToPersonnelNuit(PersonnelNuitDto personnelNuitDto){
        PersonnelNuit personnelNuit = new PersonnelNuit(
                personnelNuitDto.getId(),
                personnelNuitDto.getResponsable()
        );

        if(personnelNuitDto.getPersonnel()!=null){
            personnelNuit.setPersonnel(PersonnelController.convertDtoToPersonnel(personnelNuitDto.getPersonnel()));
        }

        if(personnelNuitDto.getPermanence()!=null){
            personnelNuit.setPermanence(PermanenceController.convertDtoToPermanence(personnelNuitDto.getPermanence()));

        }

        return personnelNuit;
    }
}
