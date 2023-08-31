package com.example.demo.controllers;


import com.example.demo.controllers.abstracts.modelConvert.RoleConvertController;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.dto.RoleDto;
import com.example.demo.dto.PermanenceDto;
import com.example.demo.entities.Role;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.Role;
import com.example.demo.entities.Permanence;
import com.example.demo.services.RoleService;
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

@RestController
@CrossOrigin
@RequestMapping(path = "role")
public class RoleController extends RoleConvertController {
    @Autowired
    RoleService roleService;

    @DeleteMapping(path = "{id}/personnel/{id2}")
    public ResponseEntity<RoleDto> deletePersonnel(@PathVariable Long id, @PathVariable Long id2){
        Role role = roleService.deletePersonnel(id, id2);
        if(role==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertRoleToDto(role, 1));
    }

    @PutMapping(path = "{id}/add-personnel/{id2}")
    public ResponseEntity<RoleDto> addPersonnel(@PathVariable Long id, @PathVariable Long id2){
        Role role = roleService.addPersonnel(id, id2);
        if(role==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertRoleToDto(role, 1));
    }

    @PutMapping(path = "{id}/add-personnels")
    public ResponseEntity<RoleDto> addPersonnels(@PathVariable Long id, @RequestBody RoleDto roleDto){
        Role role = convertDtoToRole(roleDto);
        role = roleService.addPersonnels(id, role);
        if(role==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertRoleToDto(role, 1));
    }

    @PutMapping(path = "giveAll/{userId}")
    public ResponseEntity<PersonnelDto> giveAllRoles(@PathVariable Long userId){
        Personnel personnel = roleService.giveAllRoles(userId);
        if(personnel==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(PersonnelController.convertPersonnelToDto(personnel, 0, 0, 0, 0, 0, 0, 1, 0, 0));
    }
}