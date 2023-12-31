package com.example.demo.services;

import com.example.demo.dto.RemplacementDto;
import com.example.demo.entities.Month;
import com.example.demo.entities.Remplacement;
import com.example.demo.repositories.MonthRepository;
import com.example.demo.repositories.RemplacementRepository;
import com.example.demo.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemplacementService extends BaseService<Remplacement, RemplacementRepository> {

    @Autowired
    RemplacementRepository remplacementRepository;

    public Remplacement update(Long id, Remplacement update_remplacement){
        Remplacement remplacement = remplacementRepository.findById(id).orElse(null);
        if(remplacement==null) return null;
        remplacement.setMessage(update_remplacement.getMessage());
        remplacement.setMotif(update_remplacement.getMotif());
        remplacement.setValidate(update_remplacement.getValidate());
        remplacement.setStart(update_remplacement.getStart());
        remplacement.setFin(update_remplacement.getFin());
        return remplacementRepository.save(remplacement);
    }

/*    public Remplacement update(Remplacement remplacementUpdate, Long id){
        Remplacement remplacement1 = remplacementRepository.findById(id).orElse(null);
        if(remplacement1==null) return null;
        if(!remplacement1.getId().equals(remplacementUpdate.getId())) return null;
        return remplacementRepository.save(remplacementUpdate);
    }*/

}
