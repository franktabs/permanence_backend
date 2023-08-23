package com.example.demo.services;

import com.example.demo.entities.Departement;
import com.example.demo.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public Departement create(Departement departement){
        return departementRepository.save(departement);
    }


    public Departement update(Departement departementUpdate, Long id){
        Departement departement1 = departementRepository.findById(id).orElse(null);
        if(departement1==null) return null;
        if(!departement1.getId().equals(departementUpdate.getId())) return null;
        return departementRepository.save(departementUpdate);
    }

    public List<Departement> getAllDepartement(){
        return departementRepository.findAll();
    }

    public Departement getDepartementById(Long id){
        return departementRepository.findById(id).orElse(null);
    }
}
