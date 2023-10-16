package com.afriland.dsi.permanence.controllers.abstracts.modelConvert;

import com.afriland.dsi.permanence.controllers.PermanenceController;
import com.afriland.dsi.permanence.controllers.PersonnelController;
import com.afriland.dsi.permanence.controllers.abstracts.ModelConvertController;
import com.afriland.dsi.permanence.dto.PersonnelNuitDto;
import com.afriland.dsi.permanence.entities.PersonnelNuit;
import com.afriland.dsi.permanence.services.PersonnelNuitService;

public class PersonnelNuitConvertController extends ModelConvertController<PersonnelNuit, PersonnelNuitDto, PersonnelNuitService> {
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
