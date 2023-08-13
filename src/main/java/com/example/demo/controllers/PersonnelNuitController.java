package com.example.demo.controllers;

import com.example.demo.dto.PersonnelDto;
import com.example.demo.dto.PersonnelNuitDto;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.PersonnelNuit;

import java.util.HashSet;
import java.util.Set;

public class PersonnelNuitController {

    public static PersonnelNuitDto convertPersonnelNuitToDto(PersonnelNuit personnelNuit, int depthPersonnel, int depthPermanence){
        PersonnelNuitDto personnelNuitDto = new PersonnelNuitDto(
                personnelNuit.getId(),
                personnelNuit.getResponsable()
        );

        if(depthPersonnel>0){
            if(personnelNuit.getPersonnel()!=null){
               personnelNuitDto.setPersonnel(PersonnelController.convertPersonnelToDto(personnelNuit.getPersonnel(), 1, 1, 1,depthPersonnel-1, 1));
            }
        }

        if(depthPermanence > 0){
            if(personnelNuit.getPermanence()!=null){
                personnelNuitDto.setPermanence(PermanenceController.convertPermanenceToDto(personnelNuit.getPermanence(), 1, depthPermanence-1, 1));
            }
        }

        return personnelNuitDto;
    }

    public static PersonnelNuit convertDtoToPersonnelNuit(PersonnelNuitDto personnelNuitDto){
        PersonnelNuit personnelNuit = new PersonnelNuit(
                personnelNuitDto.getId(),
                personnelNuitDto.getResponsable()
        );

        if(personnelNuitDto.getPersonnel()!=null){
            personnelNuit.setPersonnel(PersonnelController.convertDtoToPersonnel(personnelNuitDto.getPersonnel()));
        }

        if(personnelNuitDto.getPermanence()!=null){
            personnelNuit.setPermanence(PermanenceController.convertDtoToPermanence(personnelNuitDto.getPermanence()));

        }

        return personnelNuit;
    }
}
