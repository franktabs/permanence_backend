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

import java.util.*;

@Service
public class PlanningService extends BaseService<Planning, PlanningRepository> {

    @Autowired
    PermanenceRepository permanenceRepository;

    @Autowired
    PlanningRepository planningRepository;

    public Set<PlanningDto> findPlanningByPersonnelId(Long id) {
        List<Permanence> permanences = permanenceRepository.findByPersonnelJours_Personnel_IdOrPersonnelNuits_Personnel_Id(id, id);
        if (permanences == null) return null;
        Set<PlanningDto> planningDtoList = new HashSet<>();
        Map<Long, Planning> planningMap = new HashMap<>();
        Map<Long, Month> monthMap = new HashMap<>();

        Set<Long> idListMonths = new HashSet<>();
        Set<Long> idListPlanning = new HashSet<>();

        for (Permanence permanence : permanences) {
            Month month = permanence.getMonth();
            Planning planning = month.getPlanning();

            if (!idListPlanning.contains(planning.getId())) {

                if(!idListMonths.contains(month.getId())){

                    month.setPermanences(new HashSet<>(Set.of(permanence)));
                    month.getPermanences().add(permanence);
                    monthMap.put(month.getId(), month);
                    idListMonths.add(month.getId());
                }else{
                    month = monthMap.get(month.getId());
                    month.getPermanences().add(permanence);
                }
                planning.setMonths(new HashSet<>(Set.of(month)));
                idListPlanning.add(planning.getId());
                planningMap.put(planning.getId(), planning);

            }else{
                planning = planningMap.get(planning.getId());
                if(!idListMonths.contains(month.getId())){
                    month.setPermanences(new HashSet<>(Set.of(permanence)));
                    month.getPermanences().add(permanence);
                    monthMap.put(month.getId(), month);
                    idListMonths.add(month.getId());
                }else{
                    month = monthMap.get(month.getId());
                    month.getPermanences().add(permanence);
                }
                System.out.println("Moi à l'ajout "+ idListMonths+" \n Planning => "+idListPlanning);
                System.out.println("Ajout du mois => "+planning.getMonths().add(month));
            }

        }

        for(Planning planning : planningMap.values()){
            PlanningDto planningDto = PlanningController.convertPlanningToDto(planning, 1);
            planningDtoList.add(planningDto);
        }
        return planningDtoList;
    }
}
