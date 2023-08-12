package com.example.demo.controllers;

import com.example.demo.dto.RemplacementDto;
import com.example.demo.entities.Remplacement;

import static com.example.demo.controllers.PersonnelController.convertPersonnelToDto;

public class RemplacementController {

    public static RemplacementDto convertRemplacementToDto(Remplacement remplacement, int depthPersonnel, int depthRemplaceur){
        RemplacementDto remplacementDto = new RemplacementDto(
                remplacement.getId(),
                remplacement.getMessage(),
                remplacement.getMotif(),
                remplacement.getValidate(),
                remplacement.getSubmissionDate(),
                remplacement.getStart(),
                remplacement.getEnd()
        );
        if(depthPersonnel>0){
            if(remplacement.getPersonnel()!=null){
                remplacementDto.setPersonnel(convertPersonnelToDto(remplacement.getPersonnel(), 1, 1, depthPersonnel -1));
            }
        }
        if(depthRemplaceur>0){
            if(remplacement.getRemplaceur()!=null){
                remplacementDto.setRemplaceur(convertPersonnelToDto(remplacement.getRemplaceur(), 1, 1, 1));
            }
        }

        return remplacementDto;
    }

    public static Remplacement convertDtoToRemplacement(RemplacementDto remplacementDto) {
        Remplacement remplacement = new Remplacement(
                remplacementDto.getId(),
                remplacementDto.getMessage(),
                remplacementDto.getMotif(),
                remplacementDto.getValidate(),
                remplacementDto.getSubmissionDate(),
                remplacementDto.getStart(),
                remplacementDto.getEnd()
        );
        if(remplacementDto.getPersonnel()!=null){
            remplacement.setPersonnel(PersonnelController.convertDtoToPersonnel(remplacementDto.getPersonnel()));
        }
        if(remplacementDto.getRemplaceur()!=null){
            remplacement.setRemplaceur(PersonnelController.convertDtoToPersonnel(remplacementDto.getRemplaceur()));
        }
        return remplacement;
    }
}
