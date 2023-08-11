package com.example.demo.controllers;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "personnel")
public class PersonnelController {

    @Autowired
    PersonnelService personnelService;

    @Autowired
    Validator validator;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Personnel> allPersonnel(){
        return personnelService.getAllPerson();
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

}
