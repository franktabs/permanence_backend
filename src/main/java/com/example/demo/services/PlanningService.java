package com.example.demo.services;

import com.example.demo.controllers.PlanningController;
import com.example.demo.dto.PermanenceDto;
import com.example.demo.dto.PlanningDto;
import com.example.demo.entities.Month;
import com.example.demo.entities.Permanence;
import com.example.demo.entities.Planning;
import com.example.demo.repositories.MonthRepository;
import com.example.demo.repositories.PermanenceRepository;
import com.example.demo.repositories.PlanningRepository;
import com.example.demo.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlanningService extends BaseService<Planning, PlanningRepository> {

    @Autowired
    PermanenceRepository permanenceRepository;

    @Autowired
    PlanningRepository planningRepository;

    public Set<PlanningDto> findPlanningByPersonnelId(Long id) {
        List<Permanence> permanences = permanenceRepository.findByPersonnelJours_Personnel_IdOrPersonnelNuits_Personnel_Id(id, id);
        if (permanences == null) return null;
        long count = planningRepository.count();
        Set<PlanningDto> planningDtoList = new HashSet<>();
        Set<Long> idListPlanning = new HashSet<>();
        for (Permanence permanence : permanences) {
            Planning planning = permanence.getMonth().getPlanning();
            if (!idListPlanning.contains(planning.getId())) {

                idListPlanning.add(planning.getId());
                PlanningDto planningDto = PlanningController.convertPlanningToDto(planning, 1);
                planningDtoList.add(planningDto);
            }
            if (planningDtoList.size() >= count) {
                break;
            }

        }
        return planningDtoList;
    }
}
