package com.example.demo.services;

import com.example.demo.entities.Absence;
import com.example.demo.entities.Absence;
import com.example.demo.repositories.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    public Absence create(Absence absence){
        return absenceRepository.save(absence);
    }


    public Absence update(Absence absenceUpdate, Long id){
        Absence absence1 = absenceRepository.findById(id).orElse(null);
        if(absence1==null) return null;
        if(!absence1.getId().equals(absenceUpdate.getId())) return null;
        return absenceRepository.save(absenceUpdate);
    }

    public List<Absence> getAllAbsence(){
        return absenceRepository.findAll();
    }

    public Absence getAbsenceById(Long id){
        return absenceRepository.findById(id).orElse(null);
    }
}
