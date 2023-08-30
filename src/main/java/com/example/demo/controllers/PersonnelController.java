package com.example.demo.controllers;

import com.example.demo.controllers.abstracts.modelConvert.PersonnelConvertController;
import com.example.demo.dto.*;
import com.example.demo.entities.*;
import com.example.demo.enumeration.Config;
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
public class PersonnelController extends PersonnelConvertController {

    @Autowired
    PersonnelService personnelService ;

    @Autowired
    Validator validator;


    @GetMapping(path = "/userId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonnelDto> getPersonnelByUserId(@PathVariable Long id) {
        Personnel personnel = personnelService.getByUserId(id);
        if (personnel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertPersonnelToDto(personnel, 1, 1, 1, 1, 1, 1, 1, 1, 1));
    }


    @PostMapping(path = "/config-actualise")
    public ResponseEntity<?> configActualise(@Valid @RequestBody List<PersonnelDto> personnelDtos, BindingResult bindingResult) {
        try{
            if (bindingResult.hasErrors()) {
                return actionError(bindingResult);
            }
            List<PersonnelDto> ligne = personnelService.configPersonnel(personnelDtos, Config.MISE_A_JOUR);
            return ResponseEntity.ok().body(ligne);
        }
        catch (DataIntegrityViolationException e){
            return catchMessageDataIntegrity(e);
        }
        catch (Exception e){
            return catchMessageException(e);
        }
    }

    @PostMapping(path = "/config-recreate")
    public ResponseEntity<?> configRecreate(@Valid @RequestBody List<PersonnelDto> personnelDtos, BindingResult bindingResult) {
        try{
            if (bindingResult.hasErrors()) {
               return actionError(bindingResult);
            }
            List<PersonnelDto> ligne = personnelService.configPersonnel(personnelDtos, Config.RECREATE);
            return ResponseEntity.ok().body( ligne);
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


}
