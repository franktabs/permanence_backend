package com.example.demo.controllers;

import com.example.demo.dto.DepartementDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Personnel;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.controllers.DirectionController.convertDirectionToDTO;
import static com.example.demo.controllers.PersonnelController.convertPersonnelToDto;

public class DepartementController {



    public static DepartementDto convertDepartementToDto(Departement departement, int depthDirection, int depthPersonnel){
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
                dto.setDirection(convertDirectionToDTO(departement.getDirection(), depthDirection - 1));
            }

        }
        if(depthPersonnel > 0){

            Set<PersonnelDto> personnelDtos = new HashSet<>();
            for(Personnel personnel:departement.getPersonnels()){
                personnelDtos.add(convertPersonnelToDto(personnel, depthPersonnel - 1));
            }
        }
        return dto;
    }
}
