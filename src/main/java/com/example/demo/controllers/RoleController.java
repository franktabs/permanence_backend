package com.example.demo.controllers;


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
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDto>> allRole() {
        List<Role> roles = roleService.getAllRole();
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            roleDtos.add(convertRoleToDto(role, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(roleDtos);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> getOneRole(@PathVariable Long id){
        Role role = roleService.getRoleById(id);
        if(role==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertRoleToDto(role, 1));
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody RoleDto roleDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Role role = convertDtoToRole(roleDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertRoleToDto(roleService.create(role), 1));

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

    public static RoleDto convertRoleToDto(Role role, int depthPersonnel) {
        RoleDto roleDto = new RoleDto(
                role.getId(),
                role.getName()
        );

        if (depthPersonnel > 0) {
            Set<PersonnelDto> personnelDtos = new HashSet<>();
            if (role.getPersonnels() != null) {
                for (Personnel personnel : role.getPersonnels()) {
                    personnelDtos.add(PersonnelController.convertPersonnelToDto(personnel, 0, 0, 0, 0, 0, 0, depthPersonnel-1, 0, 0));
                }
            }

            roleDto.setPersonnels(personnelDtos);
        }

        return roleDto;
    }

    public static Role convertDtoToRole(RoleDto roleDto){
        Role role = new Role(
                roleDto.getId(),
                roleDto.getName()
        );

        Set<Personnel> personnels = new HashSet<>();
        if(roleDto.getPersonnels()!=null){
            for(PersonnelDto personnelDto : roleDto.getPersonnels()){
                personnels.add(PersonnelController.convertDtoToPersonnel(personnelDto));
            }
        }
        role.setPersonnels(personnels);

        return role;
    }
}
