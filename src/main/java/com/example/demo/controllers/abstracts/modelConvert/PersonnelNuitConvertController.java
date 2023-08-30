package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.PermanenceController;
import com.example.demo.controllers.PersonnelController;
import com.example.demo.controllers.abstracts.ModelBaseConvertController;
import com.example.demo.dto.PersonnelNuitDto;
import com.example.demo.entities.PersonnelNuit;
import com.example.demo.services.PersonnelNuitService;

public class PersonnelNuitConvertController extends ModelBaseConvertController<PersonnelNuit, PersonnelNuitDto, PersonnelNuitService> {
    @Override
    public PersonnelNuitDto convertModelToDto(PersonnelNuit model, int... depth) {
        return convertPersonnelNuitToDto(model, depth[0], depth[1]);
    }

    @Override
    public PersonnelNuit convertDtoToModel(PersonnelNuitDto modelDto) {
        return convertDtoToPersonnelNuit(modelDto);
    }

    public static PersonnelNuitDto convertPersonnelNuitToDto(PersonnelNuit personnelNuit, int depthPersonnel, int depthPermanence){
        PersonnelNuitDto personnelNuitDto = new PersonnelNuitDto(
                personnelNuit.getId(),
                personnelNuit.getResponsable(),
                personnelNuit.getIsSubstitute()
        );

        if(depthPersonnel>0){
            if(personnelNuit.getPersonnel()!=null){
                personnelNuitDto.setPersonnel(PersonnelController.convertPersonnelToDto(personnelNuit.getPersonnel(), 1,1, 1, depthPersonnel-1, 1, 1, 1, 0, 0));
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
                personnelNuitDto.getResponsable(),
                personnelNuitDto.getIsSubstitute()
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
