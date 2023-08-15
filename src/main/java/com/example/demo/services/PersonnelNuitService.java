package com.example.demo.services;

import com.example.demo.entities.PersonnelNuit;
import com.example.demo.repositories.PersonnelNuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelNuitService {

    @Autowired
    PersonnelNuitRepository personnelNuitRepository;

    public PersonnelNuit create(PersonnelNuit personnelNuit){
        return personnelNuitRepository.save(personnelNuit);
    }


    public PersonnelNuit update(PersonnelNuit personnelNuitUpdate, Long id){
        PersonnelNuit personnelNuit1 = personnelNuitRepository.findById(id).orElse(null);
        if(personnelNuit1==null) return null;
        if(!personnelNuit1.getId().equals(personnelNuitUpdate.getId())) return null;
        return personnelNuitRepository.save(personnelNuitUpdate);
    }

    public List<PersonnelNuit> getAllPersonnelNuit(){
        return personnelNuitRepository.findAll();
    }

    public PersonnelNuit getPersonnelNuitById(Long id){
        return personnelNuitRepository.findById(id).orElse(null);
    }


    public boolean delete(Long id){

        if(!personnelNuitRepository.existsById(id)){
            return false;
        }
        personnelNuitRepository.deleteById(id);
        return true;
    }
}
