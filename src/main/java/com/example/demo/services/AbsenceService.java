package com.example.demo.services;

import com.example.demo.entities.Absence;
import com.example.demo.entities.Absence;
import com.example.demo.entities.Month;
import com.example.demo.repositories.AbsenceRepository;
import com.example.demo.repositories.MonthRepository;
import com.example.demo.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService extends BaseService<Absence, AbsenceRepository> {

    @Autowired
    private AbsenceRepository absenceRepository;
    
    public Absence update(Absence absenceUpdate, Long id){
        Absence absence = absenceRepository.findById(id).orElse(null);
        if(absence==null) return null;
        if(!absence.getId().equals(absenceUpdate.getId())) return null;
        absence.setEnd(absenceUpdate.getEnd());
        absence.setMessage(absenceUpdate.getMessage());
        absence.setMotif(absenceUpdate.getMotif());
        absence.setStart(absenceUpdate.getStart());
        absence.setType(absenceUpdate.getType());
        absence.setValidate(absenceUpdate.getValidate());
        absence.setSubmissionDate(absenceUpdate.getSubmissionDate());

        return absenceRepository.save(absence);
    }

}
