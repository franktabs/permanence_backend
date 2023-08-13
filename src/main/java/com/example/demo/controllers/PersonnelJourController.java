package com.example.demo.controllers;

import com.example.demo.dto.PersonnelJourDto;
import com.example.demo.dto.PersonnelNuitDto;
import com.example.demo.entities.PersonnelJour;
import com.example.demo.entities.PersonnelNuit;

public class PersonnelJourController {


    public static PersonnelJourDto convertPersonnelJourToDto(PersonnelJour personnelJour, int depthPersonnel, int depthPermanence){
        PersonnelJourDto personnelJourDto = new PersonnelJourDto(
                personnelJour.getId(),
                personnelJour.getResponsable()
        );

        if(depthPersonnel>0){
            if(personnelJour.getPersonnel()!=null){
                personnelJourDto.setPersonnel(PersonnelController.convertPersonnelToDto(personnelJour.getPersonnel(), 1, 1, 1, 1,depthPersonnel-1));
            }
        }

        if(depthPermanence > 0){
            if(personnelJour.getPermanence()!=null){
                personnelJourDto.setPermanence(PermanenceController.convertPermanenceToDto(personnelJour.getPermanence(), depthPersonnel-1, 1, 1));
            }
        }

        return personnelJourDto;
    }


    public static PersonnelJour convertDtoToPersonnelJour(PersonnelJourDto personnelJourDto){
        PersonnelJour personnelJour = new PersonnelJour(
                personnelJourDto.getId(),
                personnelJourDto.getResponsable()
        );

        if(personnelJour.getPersonnel()!=null){
            personnelJour.setPersonnel(PersonnelController.convertDtoToPersonnel(personnelJourDto.getPersonnel()));
        }

        if(personnelJour.getPermanence()!=null){
            personnelJour.setPermanence(PermanenceController.convertDtoToPermanence(personnelJourDto.getPermanence()));

        }

        return personnelJour;
    }
}
