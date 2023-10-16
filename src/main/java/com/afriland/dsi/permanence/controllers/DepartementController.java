package com.afriland.dsi.permanence.controllers;

import com.afriland.dsi.permanence.controllers.abstracts.modelConvert.DepartementConvertController;

import com.afriland.dsi.permanence.dto.DepartementDto;
import com.afriland.dsi.permanence.dto.DirectionDto;
import com.afriland.dsi.permanence.entities.Departement;
import com.afriland.dsi.permanence.entities.Direction;
import com.afriland.dsi.permanence.repositories.DepartementRepository;
import com.afriland.dsi.permanence.services.DepartementService;
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
