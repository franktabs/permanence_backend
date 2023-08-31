package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.PersonnelController;
import com.example.demo.controllers.abstracts.ModelConvertController;
import com.example.demo.dto.RemplacementDto;
import com.example.demo.entities.Remplacement;
import com.example.demo.services.RemplacementService;

import static com.example.demo.controllers.abstracts.modelConvert.PersonnelConvertController.convertPersonnelToDto;

public class RemplacemtConvertController extends ModelConvertController<Remplacement, RemplacementDto, RemplacementService> {
    @Override
    public RemplacementDto convertModelToDto(Remplacement model, int... depth) {
        return convertRemplacementToDto(model, depth[0], depth[1]);
    }

    @Override
    public Remplacement convertDtoToModel(RemplacementDto modelDto) {
        return convertDtoToRemplacement(modelDto);
    }

    public static RemplacementDto convertRemplacementToDto(Remplacement remplacement, int depthPersonnel, int depthRemplaceur){
        RemplacementDto remplacementDto = new RemplacementDto(
                remplacement.getId(),
                remplacement.getMessage(),
                remplacement.getMotif(),
                remplacement.getValidate(),
                remplacement.getSubmissionDate(),
                remplacement.getStart(),
                remplacement.getFin()
        );
        if(depthPersonnel>0){
            if(remplacement.getPersonnel()!=null){
                remplacementDto.setPersonnel(convertPersonnelToDto(remplacement.getPersonnel(), 1, 1, depthPersonnel -1, 1, 1, 1, 1, 0, 0));
            }
        }
        if(depthRemplaceur>0){
            if(remplacement.getRemplaceur()!=null){
                remplacementDto.setRemplaceur(convertPersonnelToDto(remplacement.getRemplaceur(), 1, 1, depthRemplaceur-1, 1, 1, 1, 1, 0, 0));
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
