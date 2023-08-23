package com.example.demo.services;

import com.example.demo.controllers.PersonnelController;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.entities.Personnel;
import com.example.demo.enumeration.Config;
import com.example.demo.repositories.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    @Autowired
    PersonnelRepository personnelRepository;

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

    public void configPersonnel(List<PersonnelDto> personnelDtos, Config config){
        if(config==Config.RECREATE){
            personnelRepository.deleteAll();
        }
        for(PersonnelDto personnelDto : personnelDtos){
            Personnel newPersonnel = PersonnelController.convertDtoToPersonnel(personnelDto);
            Personnel personnel = personnelRepository.findByUserId(newPersonnel.getUserId());
            if(personnel==null){
                creer(newPersonnel);
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

                creer(newPersonnel);

            }
        }
    }
}
