package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.abstracts.ModelConvertController;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.dto.GroupeDto;
import com.example.demo.dto.GroupeDto;
import com.example.demo.dto.CritereDto;
import com.example.demo.dto.interfaces.ModelDto;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.Groupe;
import com.example.demo.entities.Groupe;
import com.example.demo.entities.Critere;
import com.example.demo.entities.interfaces.Model;
import com.example.demo.services.GroupeService;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.demo.controllers.abstracts.modelConvert.CritereConvertController.convertDtoToCritere;
import static com.example.demo.controllers.abstracts.modelConvert.PersonnelConvertController.convertPersonnelToDto;
import static com.example.demo.controllers.abstracts.modelConvert.PersonnelConvertController.convertDtoToPersonnel;
import static com.example.demo.controllers.abstracts.modelConvert.CritereConvertController.convertCritereToDto;

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
