package com.example.demo.services;

import com.example.demo.entities.Absence;
import com.example.demo.repositories.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    public Absence createAbsence(Absence absence){
        return absenceRepository.save(absence);
    }

    public Absence updateAbsence(Absence absenceUpdate, Long id){
        Absence absence = absenceRepository.findById(id).orElse(null);
        if(absence==null){
            return null;
        }
        if(!absenceUpdate.getId().equals(id)){
            return null;
        }
        return absenceRepository.save(absenceUpdate);

    }
}
