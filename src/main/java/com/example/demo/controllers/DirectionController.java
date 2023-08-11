package com.example.demo.controllers;

import com.example.demo.entities.Direction;
import com.example.demo.services.DirectionService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "direction")
public class DirectionController {

    @Autowired
    DirectionService directionService;

    @Autowired
    Validator validator;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> creerOne( @RequestBody Direction direction){
        Errors errors = new BeanPropertyBindingResult(direction, "direction");
        validator.validate(direction, errors);
        if(errors.hasErrors()){
            Map<String, String> mapErrors = new HashMap<>();
            for(FieldError error: errors.getFieldErrors()){
                mapErrors.put(error.getField(),error.getDefaultMessage());
            }
            return  ResponseEntity.badRequest().body(mapErrors);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(directionService.sauvegarder(direction));
    }


    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Direction>> getAll(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(directionService.getAllDirection());
    }

}
