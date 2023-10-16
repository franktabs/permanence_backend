package com.afriland.dsi.permanence.services;

import com.afriland.dsi.permanence.entities.Absence;
import com.afriland.dsi.permanence.entities.Absence;
import com.afriland.dsi.permanence.entities.Month;
import com.afriland.dsi.permanence.repositories.AbsenceRepository;
import com.afriland.dsi.permanence.repositories.MonthRepository;
import com.afriland.dsi.permanence.services.abstracts.BaseService;
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
        absence.setFin(absenceUpdate.getFin());
        absence.setMessage(absenceUpdate.getMessage());
        absence.setMotif(absenceUpdate.getMotif());
        absence.setStart(absenceUpdate.getStart());
        absence.setType(absenceUpdate.getType());
        absence.setValidate(absenceUpdate.getValidate());
        absence.setSubmissionDate(absenceUpdate.getSubmissionDate());

        return absenceRepository.save(absence);
    }

}
