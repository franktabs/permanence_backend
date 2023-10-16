package com.afriland.dsi.permanence.controllers.abstracts.modelConvert;

import com.afriland.dsi.permanence.controllers.abstracts.ModelConvertController;
import com.afriland.dsi.permanence.dto.PersonnelDto;
import com.afriland.dsi.permanence.dto.GroupeDto;
import com.afriland.dsi.permanence.dto.GroupeDto;
import com.afriland.dsi.permanence.dto.CritereDto;
import com.afriland.dsi.permanence.dto.interfaces.ModelDto;
import com.afriland.dsi.permanence.entities.Personnel;
import com.afriland.dsi.permanence.entities.Groupe;
import com.afriland.dsi.permanence.entities.Groupe;
import com.afriland.dsi.permanence.entities.Critere;
import com.afriland.dsi.permanence.entities.interfaces.Model;
import com.afriland.dsi.permanence.services.GroupeService;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.afriland.dsi.permanence.controllers.abstracts.modelConvert.CritereConvertController.convertDtoToCritere;
import static com.afriland.dsi.permanence.controllers.abstracts.modelConvert.PersonnelConvertController.convertPersonnelToDto;
import static com.afriland.dsi.permanence.controllers.abstracts.modelConvert.PersonnelConvertController.convertDtoToPersonnel;
import static com.afriland.dsi.permanence.controllers.abstracts.modelConvert.CritereConvertController.convertCritereToDto;

public class GroupeConvertController extends ModelConvertController<Groupe, GroupeDto, GroupeService> {

    @Override
    public GroupeDto convertModelToDto(Groupe model, int... depth) {
        return convertGroupeToDto(model, 1, 1);
    }

    @Override
    public Groupe convertDtoToModel(GroupeDto modelDto) {
        return convertDtoToGroupe(modelDto);
    }


    public static GroupeDto convertGroupeToDto(Groupe groupe, int depthPersonnel, int depthCritere) {
        GroupeDto groupeDto = new GroupeDto(
                groupe.getId(),
                groupe.getNom()

        );
        if (depthPersonnel > 0) {

            Set<PersonnelDto> personnelDto = new HashSet<>();
            for (Personnel personnel : groupe.getPersonnels()) {
                personnelDto.add(convertPersonnelToDto(personnel, 0, 0,0, 0, 0, 0, 0, 0, 0));
            }

            groupeDto.setPersonnels(personnelDto);
        }

        if (depthCritere > 0) {

            Set<CritereDto> parameterDto = new HashSet<>();
            for (Critere parameter : groupe.getCriteres()) {
                parameterDto.add(convertCritereToDto(parameter, depthCritere - 1));
            }

            groupeDto.setCriteres(parameterDto);
        }

        return groupeDto;


    }


    public static Groupe convertDtoToGroupe(GroupeDto groupeDto) {
        Groupe groupe = new Groupe(
                groupeDto.getId(),
                groupeDto.getNom()

        );
        Set<Personnel> personnels = new HashSet<>();
        if(groupeDto.getPersonnels()!=null){
            for (PersonnelDto personnelDto : groupeDto.getPersonnels()) {
                personnels.add(convertDtoToPersonnel(personnelDto));
            }
        }
        groupe.setPersonnels(personnels);

        Set<Critere> criteres = new LinkedHashSet<>();
        if(groupeDto.getCriteres()!=null){
            for(CritereDto critereDto: groupeDto.getCriteres()){
                criteres.add(convertDtoToCritere(critereDto));
            }
        }

        return groupe;
    }
}
