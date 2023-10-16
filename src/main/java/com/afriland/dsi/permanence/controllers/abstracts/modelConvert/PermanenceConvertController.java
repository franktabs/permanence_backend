package com.afriland.dsi.permanence.controllers.abstracts.modelConvert;

import com.afriland.dsi.permanence.controllers.MonthController;
import com.afriland.dsi.permanence.controllers.PersonnelJourController;
import com.afriland.dsi.permanence.controllers.PersonnelNuitController;
import com.afriland.dsi.permanence.controllers.abstracts.ModelConvertController;
import com.afriland.dsi.permanence.dto.PermanenceDto;
import com.afriland.dsi.permanence.dto.PersonnelJourDto;
import com.afriland.dsi.permanence.dto.PersonnelNuitDto;
import com.afriland.dsi.permanence.entities.Permanence;
import com.afriland.dsi.permanence.entities.PersonnelJour;
import com.afriland.dsi.permanence.entities.PersonnelNuit;
import com.afriland.dsi.permanence.services.PermanenceService;

import java.util.HashSet;
import java.util.Set;

public class PermanenceConvertController extends ModelConvertController<Permanence, PermanenceDto, PermanenceService> {



    public static PermanenceDto convertPermanenceToDto(Permanence permanence, int depthPersonnelJour, int depthPersonnelNuit, int depthMonth) {
        PermanenceDto permanenceDto = new PermanenceDto(
                permanence.getId(),
                permanence.getDate(),
                permanence.getType()
        );

        if (depthPersonnelNuit > 0) {
            Set<PersonnelNuitDto> personnelNuitDtos = new HashSet<>();
            if (permanence.getPersonnelNuits() != null) {
                for (PersonnelNuit personnelNuit : permanence.getPersonnelNuits()) {
                    personnelNuitDtos.add(PersonnelNuitController.convertPersonnelNuitToDto(personnelNuit, 1, depthPersonnelNuit - 1));
                }
            }

            permanenceDto.setPersonnels_nuit(personnelNuitDtos);
        }
        if (depthPersonnelJour > 0) {
            Set<PersonnelJourDto> personnelJourDtos = new HashSet<>();
            if (permanence.getPersonnelJours() != null) {
                for (PersonnelJour personnelJour : permanence.getPersonnelJours()) {
                    personnelJourDtos.add(PersonnelJourController.convertPersonnelJourToDto(personnelJour, 1, depthPersonnelJour - 1));
                }
            }
            permanenceDto.setPersonnels_jour(personnelJourDtos);
        }

        if (depthMonth > 0) {
            if (permanence.getMonth() != null) {
                permanenceDto.setMonth(MonthController.convertMonthToDto(permanence.getMonth(), depthMonth - 1, 1, 1));
            }
        }


        return permanenceDto;
    }

    public static Permanence convertDtoToPermanence(PermanenceDto permanenceDto) {
        Permanence permanence = new Permanence(
                permanenceDto.getId(),
                permanenceDto.getDate(),
                permanenceDto.getType()
        );

        Set<PersonnelJour> personnelJours = new HashSet<>();
        if (permanenceDto.getPersonnels_jour() != null) {
            for (PersonnelJourDto personnelJourDto : permanenceDto.getPersonnels_jour()) {
                personnelJours.add(PersonnelJourController.convertDtoToPersonnelJour(personnelJourDto));
            }
        }
        permanence.setPersonnelJours(personnelJours);

        Set<PersonnelNuit> personnelNuit = new HashSet<>();
        if (permanenceDto.getPersonnels_nuit() != null) {
            for (PersonnelNuitDto personnelNuitDto : permanenceDto.getPersonnels_nuit()) {
                personnelNuit.add(PersonnelNuitController.convertDtoToPersonnelNuit(personnelNuitDto));
            }
        }
        permanence.setPersonnelNuits(personnelNuit);

        if (permanenceDto.getMonth() != null) {
            permanence.setMonth(MonthController.convertDtoToMonth(permanenceDto.getMonth()));
        }

        return permanence;
    }

    @Override
    public PermanenceDto convertModelToDto(Permanence model, int... depth) {
        return convertPermanenceToDto(model, depth[0], depth[1], depth[2]);
    }

    @Override
    public Permanence convertDtoToModel(PermanenceDto modelDto) {
        return convertDtoToPermanence(modelDto);
    }
}
