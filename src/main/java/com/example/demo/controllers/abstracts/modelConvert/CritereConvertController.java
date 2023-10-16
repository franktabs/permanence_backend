package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.abstracts.ModelConvertController;
import com.example.demo.dto.GroupeDto;
import com.example.demo.dto.CritereDto;
import com.example.demo.entities.Groupe;
import com.example.demo.entities.Critere;
import com.example.demo.services.CritereService;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.controllers.abstracts.modelConvert.GroupeConvertController.convertGroupeToDto;
import static com.example.demo.controllers.abstracts.modelConvert.GroupeConvertController.convertDtoToGroupe;

public class CritereConvertController extends ModelConvertController<Critere, CritereDto, CritereService> {


    @Override
    public CritereDto convertModelToDto(Critere model, int... depth) {
        return convertCritereToDto(model, 1);
    }

    @Override
    public Critere convertDtoToModel(CritereDto modelDto) {
        return convertDtoToCritere(modelDto);
    }

    public static CritereDto convertCritereToDto(Critere critere, int depthGroupe) {
        CritereDto critereDto = new CritereDto(
                critere.getId(),
                critere.getNom()
        );
        if (depthGroupe > 0) {

            Set<GroupeDto> groupeDto = new HashSet<>();
            for (Groupe groupe : critere.getGroupes()) {
                groupeDto.add(convertGroupeToDto(groupe, 0, depthGroupe - 1));
            }

            critereDto.setGroupes(groupeDto);
        }

        return critereDto;


    }


    public static Critere convertDtoToCritere(CritereDto critereDto) {
        Critere critere = new Critere(
                critereDto.getId(),
                critereDto.getNom()

        );
        Set<Groupe> groupes = new HashSet<>();
        if (critereDto.getGroupes() != null) {
            for (GroupeDto groupeDto : critereDto.getGroupes()) {
                groupes.add(convertDtoToGroupe(groupeDto));
            }
        }
        critere.setGroupes(groupes);
        return critere;
    }
}
