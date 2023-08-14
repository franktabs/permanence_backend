package com.example.demo.services;

import com.example.demo.entities.Planning;
import com.example.demo.repositories.PlanningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanningService {

    @Autowired
    PlanningRepository planningRepository;

    public Planning create(Planning planning){
        return planningRepository.save(planning);
    }


    public Planning update(Planning planningUpdate, Long id){
        Planning planning1 = planningRepository.findById(id).orElse(null);
        if(planning1==null) return null;
        if(!planning1.getId().equals(planningUpdate.getId())) return null;
        return planningRepository.save(planningUpdate);
    }

    public List<Planning> getAllPlanning(){
        return planningRepository.findAll();
    }

    public Planning getPlanningById(Long id){
        return planningRepository.findById(id).orElse(null);
    }
}
