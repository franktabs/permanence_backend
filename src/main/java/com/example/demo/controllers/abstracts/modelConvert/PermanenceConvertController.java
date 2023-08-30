package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.MonthController;
import com.example.demo.controllers.PersonnelJourController;
import com.example.demo.controllers.PersonnelNuitController;
import com.example.demo.controllers.abstracts.ModelBaseConvertController;
import com.example.demo.dto.MonthDto;
import com.example.demo.dto.PermanenceDto;
import com.example.demo.dto.PersonnelJourDto;
import com.example.demo.dto.PersonnelNuitDto;
import com.example.demo.entities.Month;
import com.example.demo.entities.Permanence;
import com.example.demo.entities.PersonnelJour;
import com.example.demo.entities.PersonnelNuit;
import com.example.demo.services.MonthService;
import com.example.demo.services.PermanenceService;

import java.util.HashSet;
import java.util.Set;

public class PermanenceConvertController extends ModelBaseConvertController<Permanence, PermanenceDto, PermanenceService> {



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
