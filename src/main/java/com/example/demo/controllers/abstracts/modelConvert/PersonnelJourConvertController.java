package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.PermanenceController;
import com.example.demo.controllers.PersonnelController;
import com.example.demo.controllers.abstracts.ModelConvertController;
import com.example.demo.dto.PersonnelJourDto;
import com.example.demo.entities.PersonnelJour;
import com.example.demo.services.PersonnelJourService;

public class PersonnelJourConvertController extends ModelConvertController<PersonnelJour, PersonnelJourDto, PersonnelJourService> {
    @Override
    public PersonnelJourDto convertModelToDto(PersonnelJour model, int... depth) {
        return convertPersonnelJourToDto(model, depth[0], depth[1]);
    }

    @Override
    public PersonnelJour convertDtoToModel(PersonnelJourDto modelDto) {
        return convertDtoToPersonnelJour(modelDto);
    }

    public static PersonnelJourDto convertPersonnelJourToDto(PersonnelJour personnelJour, int depthPersonnel, int depthPermanence) {
        PersonnelJourDto personnelJourDto = new PersonnelJourDto(
                personnelJour.getId(),
                personnelJour.getResponsable(),
                personnelJour.getIsSubstitute()
        );

        if (depthPersonnel > 0) {
            if (personnelJour.getPersonnel() != null) {
                personnelJourDto.setPersonnel(PersonnelController.convertPersonnelToDto(personnelJour.getPersonnel(), 1, 1, 1, 1, depthPersonnel - 1, 1, 1, 0, 0));
            }
        }

        if (depthPermanence > 0) {
            if (personnelJour.getPermanence() != null) {
                personnelJourDto.setPermanence(PermanenceController.convertPermanenceToDto(personnelJour.getPermanence(), depthPersonnel - 1, 1, 1));
            }
        }

        return personnelJourDto;
    }


    public static PersonnelJour convertDtoToPersonnelJour(PersonnelJourDto personnelJourDto) {
        PersonnelJour personnelJour = new PersonnelJour(
                personnelJourDto.getId(),
                personnelJourDto.getResponsable(),
                personnelJourDto.getIsSubstitute()
        );

        if (personnelJourDto.getPersonnel() != null) {
            personnelJour.setPersonnel(PersonnelController.convertDtoToPersonnel(personnelJourDto.getPersonnel()));
        }

        if (personnelJourDto.getPermanence() != null) {
            personnelJour.setPermanence(PermanenceController.convertDtoToPermanence(personnelJourDto.getPermanence()));

        }

        return personnelJour;
    }
}
