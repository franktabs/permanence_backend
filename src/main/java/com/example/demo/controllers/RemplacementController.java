package com.example.demo.controllers;

import com.example.demo.dto.RemplacementDto;
import com.example.demo.dto.RemplacementDto;
import com.example.demo.entities.Remplacement;
import com.example.demo.entities.Remplacement;
import com.example.demo.services.RemplacementService;
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

import static com.example.demo.controllers.PersonnelController.convertPersonnelToDto;

@RestController
@CrossOrigin
@RequestMapping(path = "remplacement")
public class RemplacementController {

    @Autowired
    RemplacementService remplacementService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RemplacementDto>> allRemplacement() {
        List<Remplacement> remplacements = remplacementService.getAllRemplacement();
        List<RemplacementDto> remplacementDtos = new ArrayList<>();
        for (Remplacement remplacement : remplacements) {
            remplacementDtos.add(convertRemplacementToDto(remplacement, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(remplacementDtos);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update_validate(@PathVariable Long id, @RequestBody RemplacementDto remplacementDto){
        Remplacement remplacement = convertDtoToRemplacement(remplacementDto);
        Remplacement remplacement1 = remplacementService.update(id, remplacement);
        return ResponseEntity.status(HttpStatus.OK).body(convertRemplacementToDto(remplacement1, 1, 1));
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RemplacementDto> getOneRemplacement(@PathVariable Long id){
        Remplacement remplacement = remplacementService.getRemplacementById(id);
        if(remplacement==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertRemplacementToDto(remplacement, 1, 1));
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody RemplacementDto remplacementDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Remplacement remplacement = convertDtoToRemplacement(remplacementDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertRemplacementToDto(remplacementService.create(remplacement), 1, 1));

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

    public static RemplacementDto convertRemplacementToDto(Remplacement remplacement, int depthPersonnel, int depthRemplaceur){
        RemplacementDto remplacementDto = new RemplacementDto(
                remplacement.getId(),
                remplacement.getMessage(),
                remplacement.getMotif(),
                remplacement.getValidate(),
                remplacement.getSubmissionDate(),
                remplacement.getStart(),
                remplacement.getEnd()
        );
        if(depthPersonnel>0){
            if(remplacement.getPersonnel()!=null){
                remplacementDto.setPersonnel(convertPersonnelToDto(remplacement.getPersonnel(), 1, 1, depthPersonnel -1, 1, 1, 1, 1));
            }
        }
        if(depthRemplaceur>0){
            if(remplacement.getRemplaceur()!=null){
                remplacementDto.setRemplaceur(convertPersonnelToDto(remplacement.getRemplaceur(), 1, 1, depthRemplaceur-1, 1, 1, 1, 1));
            }
        }

        return remplacementDto;
    }

    public static Remplacement convertDtoToRemplacement(RemplacementDto remplacementDto) {
        Remplacement remplacement = new Remplacement(
                remplacementDto.getId(),
                remplacementDto.getMessage(),
                remplacementDto.getMotif(),
                remplacementDto.getValidate(),
                remplacementDto.getSubmissionDate(),
                remplacementDto.getStart(),
                remplacementDto.getEnd()
        );
        if(remplacementDto.getPersonnel()!=null){
            remplacement.setPersonnel(PersonnelController.convertDtoToPersonnel(remplacementDto.getPersonnel()));
        }
        if(remplacementDto.getRemplaceur()!=null){
            remplacement.setRemplaceur(PersonnelController.convertDtoToPersonnel(remplacementDto.getRemplaceur()));
        }
        return remplacement;
    }
}
