package com.afriland.dsi.permanence.services;

import com.afriland.dsi.permanence.controllers.PlanningController;
import com.afriland.dsi.permanence.dto.PermanenceDto;
import com.afriland.dsi.permanence.dto.PlanningDto;
import com.afriland.dsi.permanence.entities.Month;
import com.afriland.dsi.permanence.entities.Permanence;
import com.afriland.dsi.permanence.entities.Planning;
import com.afriland.dsi.permanence.repositories.MonthRepository;
import com.afriland.dsi.permanence.repositories.PermanenceRepository;
import com.afriland.dsi.permanence.repositories.PlanningRepository;
import com.afriland.dsi.permanence.services.abstracts.BaseService;
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

                if (!idListMonths.contains(month.getId())) {

                    month.setPermanences(new HashSet<>(Set.of(permanence)));
                    month.getPermanences().add(permanence);
                    monthMap.put(month.getId(), month);
                    idListMonths.add(month.getId());
                } else {
                    month = monthMap.get(month.getId());
                    month.getPermanences().add(permanence);
                }
                planning.setMonths(new HashSet<>(Set.of(month)));
                idListPlanning.add(planning.getId());
                planningMap.put(planning.getId(), planning);

            } else {
                planning = planningMap.get(planning.getId());
                if (!idListMonths.contains(month.getId())) {
                    month.setPermanences(new HashSet<>(Set.of(permanence)));
                    month.getPermanences().add(permanence);
                    monthMap.put(month.getId(), month);
                    idListMonths.add(month.getId());
                } else {
                    month = monthMap.get(month.getId());
                    month.getPermanences().add(permanence);
                }
                planning.getMonths().add(month);
            }

        }

        for (Planning planning : planningMap.values()) {
            PlanningDto planningDto = PlanningController.convertPlanningToDto(planning, 1);
            planningDtoList.add(planningDto);
        }
        return planningDtoList;
    }

    public Map<Long, Long> countPersonnelsPlanning(Long id) {
        boolean existe = planningRepository.existsById(id);
        if (existe) {
            List<Object[]> objectList = planningRepository.countAllPersonnelsPlanning(id);
            Map<Long, Long> stringMap = new TreeMap<>();
            for (Object[] object : objectList) {
                stringMap.put(Long.valueOf(object[0].toString()), Long.valueOf(object[1].toString()));
            }
            return stringMap;
        }
        return null;
    }
}
