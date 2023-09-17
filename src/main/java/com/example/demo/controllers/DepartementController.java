package com.example.demo.controllers;

import com.example.demo.controllers.abstracts.modelConvert.DepartementConvertController;

import com.example.demo.dto.DepartementDto;
import com.example.demo.dto.DirectionDto;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Direction;
import com.example.demo.repositories.DepartementRepository;
import com.example.demo.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "departement")
public class DepartementController extends DepartementConvertController {

    @Autowired
    DepartementService departementService;
    @GetMapping(path = "min-organizationId")
    public ResponseEntity<DepartementDto> getMinOrganizationId(){
        Departement departement = departementService.findMinOrganizationId();
        if(departement==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(convertDepartementToDto(departement, 0, 0));
        }
    }
}
