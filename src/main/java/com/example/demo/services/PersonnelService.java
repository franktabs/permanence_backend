package com.example.demo.services;

import com.example.demo.controllers.PersonnelController;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Personnel;
import com.example.demo.enumeration.Config;
import com.example.demo.repositories.DepartementRepository;
import com.example.demo.repositories.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    DepartementRepository departementRepository;

    public List<Personnel> getAllPerson(){
        return personnelRepository.findAll();
    }

    public Personnel creer(Personnel personnel){
        return  personnelRepository.save(personnel);
    }

    public Personnel getOnePersonnel(Long id){
        Optional<Personnel> optional =  personnelRepository.findById(id);
        return optional.orElse(null);
    }

    public List<PersonnelDto> configPersonnel(List<PersonnelDto> personnelDtos, Config config){

        List<PersonnelDto> personnelDtosSave = new ArrayList<>();
        List<Long> idList = new ArrayList<>();
        for(PersonnelDto personnelDto : personnelDtos){
            personnelDto.setId(null);
            Personnel newPersonnel = PersonnelController.convertDtoToPersonnel(personnelDto);

            Departement departement = departementRepository.findByOrganizationId(newPersonnel.getOrganizationId());

            if(departement!=null){
                newPersonnel.setDepartement(departement);
                Personnel personnel = personnelRepository.findByUserId(newPersonnel.getUserId());
                if(personnel==null){
                    Personnel register  = creer(newPersonnel);
                    idList.add(register.getId());
                    personnelDtosSave.add(PersonnelController.convertPersonnelToDto(register, 1, 1, 1, 1, 1, 1, 1, 1, 1));
                }else{
                    newPersonnel.setId(personnel.getId());
                    newPersonnel.setPersonnelJours(personnel.getPersonnelJours());
                    newPersonnel.setPersonnelNuits(personnel.getPersonnelNuits());
                    newPersonnel.setNotifications(personnel.getNotifications());
                    newPersonnel.setAnnonces(personnel.getAnnonces());
                    newPersonnel.setDepartement(personnel.getDepartement());
                    newPersonnel.setAbsences(personnel.getAbsences());
                    newPersonnel.setRemplacements(personnel.getRemplacements());
                    newPersonnel.setRoles(personnel.getRoles());
                    newPersonnel.setMonths_supervise(personnel.getMonths_supervise());

                    Personnel register  = creer(newPersonnel);
                    idList.add(register.getId());
                    personnelDtosSave.add(PersonnelController.convertPersonnelToDto(register, 1, 1, 1, 1, 1, 1, 1, 1, 1));

                }
            }

        }
        if(config==Config.RECREATE){
            List<Personnel> personnelList = personnelRepository.findAll();
            for (Personnel personnel:personnelList){
                if(!idList.contains(personnel.getId())){
                    personnelRepository.deletePersonnel(personnel.getId());
                }
            }
        }
        return personnelDtosSave;
    }
}
