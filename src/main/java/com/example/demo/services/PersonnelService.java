package com.example.demo.services;

import com.example.demo.controllers.PersonnelController;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Month;
import com.example.demo.entities.Personnel;
import com.example.demo.enumeration.Config;
import com.example.demo.repositories.DepartementRepository;
import com.example.demo.repositories.MonthRepository;
import com.example.demo.repositories.PersonnelRepository;
import com.example.demo.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService extends BaseService<Personnel, PersonnelRepository> {

    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    DepartementRepository departementRepository;

    public Personnel getByUserId(Long id){
        return personnelRepository.findByUserId(id);
    }

    public List<PersonnelDto> configPersonnel(List<PersonnelDto> personnelDtos, Config config){

        List<PersonnelDto> personnelDtosSave = new ArrayList<>();
        List<Long> idList = new ArrayList<>();
        for(PersonnelDto personnelDto : personnelDtos){
            personnelDto.setId(null);
            personnelDto.setPersonnels_jour(null);
            personnelDto.setPersonnels_nuit(null);
            personnelDto.setNotifications(null);
            personnelDto.setAnnonces(null);
            personnelDto.setAbsences(null);
            personnelDto.setRemplacements(null);
            personnelDto.setRoles(null);
            personnelDto.setMonths_supervise(null);

            Personnel newPersonnel = PersonnelController.convertDtoToPersonnel(personnelDto);

            Departement departement = departementRepository.findByOrganizationId(newPersonnel.getOrganizationId());

            if(departement!=null){
                newPersonnel.setDepartement(departement);
                Personnel personnel = personnelRepository.findByUserId(newPersonnel.getUserId());
                if(personnel==null){
                    Personnel register  = create(newPersonnel);
                    idList.add(register.getId());
                    personnelDtosSave.add(PersonnelController.convertPersonnelToDto(register, 1, 0, 0, 0, 0, 0, 0, 0, 0));
                }else{
                    newPersonnel.setId(personnel.getId());
                    newPersonnel.setPersonnelJours(personnel.getPersonnelJours());
                    newPersonnel.setPersonnelNuits(personnel.getPersonnelNuits());
                    newPersonnel.setNotifications(personnel.getNotifications());
                    newPersonnel.setAnnonces(personnel.getAnnonces());
                    if(newPersonnel.getDepartement()==null){

                    newPersonnel.setDepartement(personnel.getDepartement());
                    }
                    newPersonnel.setAbsences(personnel.getAbsences());
                    newPersonnel.setRemplacements(personnel.getRemplacements());
                    newPersonnel.setRoles(personnel.getRoles());
                    newPersonnel.setMonths_supervise(personnel.getMonths_supervise());

                    Personnel register  = create(newPersonnel);
                    idList.add(register.getId());
                    personnelDtosSave.add(PersonnelController.convertPersonnelToDto(register, 1, 0, 0, 0, 0, 0, 0, 0, 0));

                }
            }

        }
        if(config==Config.RECREATE){
            List<Personnel> personnelList = personnelRepository.findAll();
            for (Personnel personnel:personnelList){
                if(!idList.contains(personnel.getId())){
                    personnelRepository.deleteModel(personnel.getId());
                }
            }
        }
        return personnelDtosSave;
    }


}
