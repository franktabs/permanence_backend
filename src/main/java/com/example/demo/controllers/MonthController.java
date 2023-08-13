package com.example.demo.controllers;

import com.example.demo.dto.MonthDto;
import com.example.demo.dto.PermanenceDto;
import com.example.demo.entities.Month;
import com.example.demo.entities.Permanence;

import java.util.HashSet;
import java.util.Set;

public class MonthController {

    public static MonthDto convertMonthToDto(Month month, int depthPermanence, int depthPlanning) {
        MonthDto monthDto = new MonthDto(
                month.getId(),
                month.getName(),
                month.getNumero(),
                month.getStart(),
                month.getEnd()
        );

        if (depthPermanence > 0) {
            Set<PermanenceDto> permanenceDtos = new HashSet<>();
            if (month.getPermanences() != null) {
                for (Permanence permanence : month.getPermanences()) {
                    permanenceDtos.add(PermanenceController.convertPermanenceToDto(permanence, 1, 1, depthPermanence - 1));
                }
            }

            monthDto.setPermanences(permanenceDtos);
        }

        if(depthPlanning > 0){
            if(month.getPlanning()!=null){
                monthDto.setPlanning(PlanningController.convertPlanningToDto(month.getPlanning(), depthPlanning-1));
            }
        }

        return monthDto;
    }

    public static Month convertDtoToMonth(MonthDto monthDto){
        Month month = new Month(
                monthDto.getId(),
                monthDto.getName(),
                monthDto.getNumero(),
                monthDto.getStart(),
                monthDto.getEnd()
        );

        Set<Permanence> permanences = new HashSet<>();
        if(monthDto.getPermanences()!=null){
            for(PermanenceDto permanenceDto : monthDto.getPermanences()){
                permanences.add(PermanenceController.convertDtoToPermanence(permanenceDto));
            }
        }
        month.setPermanences(permanences);

        if(monthDto.getPlanning()!=null){
            month.setPlanning(PlanningController.convertDtoToPlanning(monthDto.getPlanning()));
        }

        return month;
    }
}
