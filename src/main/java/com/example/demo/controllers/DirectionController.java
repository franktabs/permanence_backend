package com.example.demo.controllers;

import com.example.demo.dto.DepartementDto;
import com.example.demo.dto.DirectionDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Direction;
import com.example.demo.entities.Personnel;
import com.example.demo.services.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.example.demo.controllers.DepartementController.convertDepartementToDto;
import static com.example.demo.controllers.DepartementController.convertDtoToDepartement;

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
    public ResponseEntity<List<DirectionDto>> getAll(){
        List<Direction> directions = directionService.getAllDirection();
        List<DirectionDto> directionDtos = new ArrayList<>();
        for(Direction direction:directions){
            directionDtos.add(convertDirectionToDTO(direction, 1));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(directionDtos);
    }

    public static DirectionDto convertDirectionToDTO(Direction direction, int depthDepartement){
        DirectionDto directionDto = new DirectionDto(
                direction.getId(),
                direction.getOrganizationId(),
                direction.getLevel(),
                direction.getType(),
                direction.getTreepath(),
                direction.getParentorganizationId(),
                direction.getName()

                );
        if (depthDepartement > 0) {

            Set<DepartementDto> departementDto = new HashSet<>();
            for (Departement departement : direction.getDepartements()) {
                departementDto.add(convertDepartementToDto(departement, depthDepartement - 1, 1));
            }

            directionDto.setDepartements(departementDto);
        }
        return directionDto;


    }


    public static Direction convertDtoToDirection(DirectionDto directionDto){
        Direction direction = new Direction(
                directionDto.getId(),
                directionDto.getOrganizationId(),
                directionDto.getLevel(),
                directionDto.getType(),
                directionDto.getTreepath(),
                directionDto.getParentorganizationId(),
                directionDto.getName()

                );
        Set<Departement> departements = new HashSet<>();
        for (DepartementDto departementDto:directionDto.getDepartements()){
            departements.add(convertDtoToDepartement(departementDto));
        }
        direction.setDepartements(departements);
    return direction;
    }


}
