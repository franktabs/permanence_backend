package com.example.demo.services;

import com.example.demo.entities.Personnel;
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
}
