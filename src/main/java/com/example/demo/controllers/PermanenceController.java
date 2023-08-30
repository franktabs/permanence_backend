package com.example.demo.controllers;

import com.example.demo.controllers.abstracts.modelConvert.PermanenceConvertController;
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
public class PermanenceController extends PermanenceConvertController {

    @Autowired
    PermanenceService permanenceService;

    @GetMapping(path = "personnel/{id}")
    public ResponseEntity<List<PermanenceDto>> getPermanenceByPersonnelId(@PathVariable Long id){
        List<PermanenceDto> permanenceDtos = permanenceService.findPermanenceByPersonnelId(id);
        return ResponseEntity.status(HttpStatus.OK).body(permanenceDtos);
    }

    @DeleteMapping(path = "entirely-personnel/{id}")
    public ResponseEntity<?> deleteEntirelyPersonnel(@PathVariable Long id) {
        System.out.println("Une operation s'est produite");
        Permanence permanence = permanenceService.deleteEntirelyPersonnel(id);
        if(permanence!=null){
            return ResponseEntity.ok().body(convertPermanenceToDto(permanence, 1, 1, 1));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("errors", "Une operation c'est mal produite"));
        }
    }

}
