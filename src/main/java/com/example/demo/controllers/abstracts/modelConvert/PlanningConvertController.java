package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.MonthController;
import com.example.demo.controllers.abstracts.ModelBaseConvertController;
import com.example.demo.dto.MonthDto;
import com.example.demo.dto.PlanningDto;
import com.example.demo.entities.Month;
import com.example.demo.entities.Planning;
import com.example.demo.services.PlanningService;

import java.util.HashSet;
import java.util.Set;

public class PlanningConvertController extends ModelBaseConvertController<Planning, PlanningDto, PlanningService> {
    @Override
    public PlanningDto convertModelToDto(Planning model, int... depth) {
        return convertPlanningToDto(model, depth[0]);
    }

    @Override
    public Planning convertDtoToModel(PlanningDto modelDto) {
        return convertDtoToPlanning(modelDto);
    }

    public static PlanningDto convertPlanningToDto(Planning planning, int depthMonth) {
        PlanningDto planningDto = new PlanningDto(
                planning.getId(),
                planning.getStart(),
                planning.getFin(),
                planning.getPeriode(),
                planning.getIsValid(),
                planning.getSubmissionDate()
        );

        if (depthMonth > 0) {
            Set<MonthDto> monthDtos = new HashSet<>();
            if (planning.getMonths() != null) {
                for (Month month : planning.getMonths()) {
                    monthDtos.add(MonthController.convertMonthToDto(month, 1, depthMonth - 1, 1));
                }
            }
            planningDto.setMonths(monthDtos);
        }

        return planningDto;
    }

    public static Planning convertDtoToPlanning(PlanningDto planningDto) {
        Planning planning = new Planning(
                planningDto.getId(),
                planningDto.getStart(),
                planningDto.getEnd(),
                planningDto.getPeriode(),
                planningDto.getIsValid(),
                planningDto.getSubmissionDate()
        );

        Set<Month> months = new HashSet<>();
        if (planningDto.getMonths() != null) {
            for (MonthDto monthDto : planningDto.getMonths()) {
                months.add(MonthController.convertDtoToMonth(monthDto));
            }
        }
        planning.setMonths(months);

        return planning;
    }
}
