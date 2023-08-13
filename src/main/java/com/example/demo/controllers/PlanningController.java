package com.example.demo.controllers;


import com.example.demo.dto.MonthDto;
import com.example.demo.dto.PlanningDto;
import com.example.demo.entities.Month;
import com.example.demo.entities.Planning;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/planning")
public class PlanningController {

    public static PlanningDto convertPlanningToDto(Planning planning, int depthMonth){
        PlanningDto planningDto = new PlanningDto(
                planning.getId(),
                planning.getStart(),
                planning.getEnd(),
                planning.getPeriode(),
                planning.getIsValid()
        );

        if(depthMonth>0){
            Set<MonthDto> monthDtos = new HashSet<>();
            if(planning.getMonths()!=null){
                for (Month month: planning.getMonths()){
                    monthDtos.add(MonthController.convertMonthToDto(month, 1, depthMonth -1));
                }
            }
            planningDto.setMonths(monthDtos);
        }

        return planningDto;
    }

    public static Planning convertDtoToPlanning(PlanningDto planningDto){
        Planning planning = new Planning(
                planningDto.getId(),
                planningDto.getStart(),
                planningDto.getEnd(),
                planningDto.getPeriode(),
                planningDto.getIsValid()
        );

        Set<Month> months = new HashSet<>();
        if(planningDto.getMonths()!=null){
            for(MonthDto monthDto:planningDto.getMonths()){
                months.add(MonthController.convertDtoToMonth(monthDto));
            }
        }
        planning.setMonths(months);

        return planning;
    }
}
