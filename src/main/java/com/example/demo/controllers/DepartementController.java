package com.example.demo.controllers;

import com.example.demo.dto.DepartementDto;
import com.example.demo.dto.DepartementDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Personnel;
import com.example.demo.services.DepartementService;
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

import static com.example.demo.controllers.DirectionController.*;
import static com.example.demo.controllers.PersonnelController.convertPersonnelToDto;

@RestController
@CrossOrigin
@RequestMapping(path = "departement")
public class DepartementController {

    @Autowired
    DepartementService departementService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DepartementDto>> allDepartement() {
        List<Departement> departements = departementService.getAllDepartement();
        List<DepartementDto> departementDtos = new ArrayList<>();
        for (Departement departement : departements) {
            departementDtos.add(convertDepartementToDto(departement, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(departementDtos);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartementDto> getOneDepartement(@PathVariable Long id){
        Departement departement = departementService.getDepartementById(id);
        if(departement==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertDepartementToDto(departement, 1, 1));
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody DepartementDto departementDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Departement departement = convertDtoToDepartement(departementDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertDepartementToDto(departementService.create(departement), 1, 1));

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

    public static DepartementDto convertDepartementToDto(Departement departement, int depthDirection, int depthPersonnel) {

        System.out.println("\n\n aprres conversion \n"+departement.toString()+"\nn");

        DepartementDto dto = new DepartementDto(
                departement.getId(),
                departement.getOrganizationId(),
                departement.getLevel(),
                departement.getType(),
                departement.getTreepath(),
                departement.getParentorganizationId(),
                departement.getName()
        );
        if (depthDirection > 0) {
            if (departement.getDirection() != null) {
                dto.setDirection(convertDirectionToDto(departement.getDirection(), depthDirection - 1, 1));
            }

        }
        if (depthPersonnel > 0) {

            Set<PersonnelDto> personnelDtos = new HashSet<>();
            for (Personnel personnel : departement.getPersonnels()) {
                personnelDtos.add(convertPersonnelToDto(personnel, depthPersonnel - 1, 1,1, 1,1, 1, 1, 0, 0));
            }

            dto.setPersonnels(personnelDtos);
        }
        return dto;
    }

    public static Departement convertDtoToDepartement(DepartementDto departementDto) {
        Departement departement = new Departement(
                departementDto.getId(),
                departementDto.getOrganizationId(),
                departementDto.getLevel(),
                departementDto.getType_(),
                departementDto.getTreePath(),
                departementDto.getParentorganizationId(),
                departementDto.getName()
        );
        if (departementDto.getDirection() != null) {
            departement.setDirection(convertDtoToDirection(departementDto.getDirection()));
        }
        Set<Personnel> personnels = new HashSet<>();
        if (departementDto.getPersonnels() != null) {

            for (PersonnelDto personnelDto : departementDto.getPersonnels()) {
                personnels.add(PersonnelController.convertDtoToPersonnel(personnelDto));
            }
        }
        departement.setPersonnels(personnels);
        return departement;
    }
}
