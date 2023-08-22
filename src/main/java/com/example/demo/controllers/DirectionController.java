package com.example.demo.controllers;

import com.example.demo.dto.DepartementDto;
import com.example.demo.dto.DirectionDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Direction;
import com.example.demo.entities.Personnel;
import com.example.demo.services.DirectionService;
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

import static com.example.demo.controllers.DepartementController.convertDepartementToDto;
import static com.example.demo.controllers.DepartementController.convertDtoToDepartement;

@RestController
@CrossOrigin
@RequestMapping(path = "direction")
public class DirectionController {

    @Autowired
    DirectionService directionService;

    @Autowired
    Validator validator;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> creerOne(@Valid @RequestBody DirectionDto directionDto, BindingResult bindingResult) {
        /*Errors errors = new BeanPropertyBindingResult(direction, "direction");
        validator.validate(direction, errors);*/

        try{
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Direction direction = convertDtoToDirection(directionDto);
            System.out.println("\n\n Conversion termninée"+ direction.toString()+" \n\n");
            direction = directionService.sauvegarder(direction);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertDirectionToDto(direction, 1));
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


    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DirectionDto>> getAll() {
        List<Direction> directions = directionService.getAllDirection();
        List<DirectionDto> directionDtos = new ArrayList<>();
        for (Direction direction : directions) {
            directionDtos.add(convertDirectionToDto(direction, 1));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(directionDtos);
    }

    public static DirectionDto convertDirectionToDto(Direction direction, int depthDepartement) {
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


    public static Direction convertDtoToDirection(DirectionDto directionDto) {
        Direction direction = new Direction(
                directionDto.getId(),
                directionDto.getOrganizationId(),
                directionDto.getLevel(),
                directionDto.getType_(),
                directionDto.getTreepath(),
                directionDto.getParentorganizationId(),
                directionDto.getName()

        );
        Set<Departement> departements = new HashSet<>();
        if(directionDto.getDepartements()!=null){
            for (DepartementDto departementDto : directionDto.getDepartements()) {
                departements.add(convertDtoToDepartement(departementDto));
            }
        }
        direction.setDepartements(departements);
        return direction;
    }


}
