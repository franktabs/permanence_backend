package com.example.demo.services;

import com.example.demo.entities.PersonnelJour;
import com.example.demo.repositories.PersonnelJourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelJourService {
    @Autowired
    PersonnelJourRepository personnelJourRepository;

    public PersonnelJour create(PersonnelJour personnelJour){
        return personnelJourRepository.save(personnelJour);
    }


    public PersonnelJour update(PersonnelJour personnelJourUpdate, Long id){
        PersonnelJour personnelJour1 = personnelJourRepository.findById(id).orElse(null);
        if(personnelJour1==null) return null;
        if(!personnelJour1.getId().equals(personnelJourUpdate.getId())) return null;
        return personnelJourRepository.save(personnelJourUpdate);
    }

    public boolean delete(Long id){

        if(!personnelJourRepository.existsById(id)){
            return false;
        }
        personnelJourRepository.deleteById(id);
        return true;
    }

    public List<PersonnelJour> getAllPersonnelJour(){
        return personnelJourRepository.findAll();
    }

    public void suppression(Long id){
        personnelJourRepository.deletePersonnelJour(id);
    }

    public PersonnelJour getPersonnelJourById(Long id){
        return personnelJourRepository.findById(id).orElse(null);
    }


}
