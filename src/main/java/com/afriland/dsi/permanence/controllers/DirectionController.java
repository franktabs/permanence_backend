package com.afriland.dsi.permanence.controllers;

import com.afriland.dsi.permanence.controllers.abstracts.modelConvert.DirectionConvertController;
import com.afriland.dsi.permanence.dto.DepartementDto;
import com.afriland.dsi.permanence.dto.DirectionDto;
import com.afriland.dsi.permanence.entities.Departement;
import com.afriland.dsi.permanence.entities.Direction;
import com.afriland.dsi.permanence.enumeration.Config;
import com.afriland.dsi.permanence.dto.interfaces.OrganisationDto;
import com.afriland.dsi.permanence.services.DirectionService;
import com.afriland.dsi.permanence.utils.StringExtract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(path = "direction")
public class DirectionController extends DirectionConvertController {


    @Autowired
    DirectionService directionService;

    @GetMapping(path = "min-organizationId")
    public ResponseEntity<DirectionDto> getMinOrganizationId() {
        Direction direction = directionService.findMinOrganizationId();
        if (direction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(convertDirectionToDto(direction, 0, 0));
        }
    }

    @PostMapping(path = "/config-actualise")
    public ResponseEntity<?> configActualise(@Valid @RequestBody List<DirectionDto> directionDtos, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return actionError(bindingResult);
            }
            List<OrganisationDto> ligne = directionService.configDirection(directionDtos, Config.MISE_A_JOUR);
            return ResponseEntity.ok().body(ligne);
        } catch (DataIntegrityViolationException e) {
            return catchMessageDataIntegrity(e);
        } catch (Exception e) {
            return catchMessageException(e);
        }
    }

    @PostMapping(path = "/config-recreate")
    public ResponseEntity<?> configRecreate(@Valid @RequestBody List<DirectionDto> directionDtos, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return actionError(bindingResult);
            }
            List<OrganisationDto> listDirection = directionService.configDirection(directionDtos, Config.RECREATE);
            return ResponseEntity.ok().body(listDirection);
        } catch (DataIntegrityViolationException e) {
            return catchMessageDataIntegrity(e);
        } catch (Exception e) {
            return catchMessageException(e);
        }
    }

/*    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> creerOne(@Valid @RequestBody DirectionDto directionDto, BindingResult bindingResult) {
        *//*Errors errors = new BeanPropertyBindingResult(direction, "direction");
        validator.validate(direction, errors);*//*

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
            return ResponseEntity.status(HttpStatus.CREATED).body(convertDirectionToDto(direction, 1, 1));
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
            directionDtos.add(convertDirectionToDto(direction, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(directionDtos);
    }*/

/*    public static DirectionDto convertDirectionToDto(Direction direction, int depthDepartement, int depthParameter) {
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

        if (depthParameter > 0) {

            Set<ParameterDto> parameterDto = new HashSet<>();
            for (Parameter parameter : direction.getParameters()) {
                parameterDto.add(convertParameterToDto(parameter, depthParameter - 1));
            }

            directionDto.setParameters(parameterDto);
        }

        return directionDto;


    }


    public static Direction convertDtoToDirection(DirectionDto directionDto) {
        Direction direction = new Direction(
                directionDto.getId(),
                directionDto.getOrganizationId(),
                directionDto.getLevel(),
                directionDto.getType_(),
                directionDto.getTreePath(),
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
    }*/


}
