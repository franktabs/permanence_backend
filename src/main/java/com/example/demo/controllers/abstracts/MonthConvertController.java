package com.example.demo.controllers.abstracts;

import com.example.demo.controllers.PermanenceController;
import com.example.demo.controllers.PersonnelController;
import com.example.demo.controllers.PlanningController;
import com.example.demo.dto.MonthDto;
import com.example.demo.dto.PermanenceDto;
import com.example.demo.entities.Month;
import com.example.demo.entities.Permanence;
import com.example.demo.services.MonthService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service()
public abstract class MonthConvertController extends ModelBaseConvertController<Month, MonthDto, MonthService> {

    @Override
    public Month convertDtoToModel(MonthDto modelDto) {
        return convertDtoToMonth(modelDto);
    }

    @Override
    public MonthDto convertModelToDto(Month model, int... depth) {
        return convertMonthToDto(model, depth[0], depth[1], depth[2]);
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

        if(monthDto.getSuperviseur()!=null){
            month.setSuperviseur(PersonnelController.convertDtoToPersonnel(monthDto.getSuperviseur()));
        }

        return month;
    }

    public static MonthDto convertMonthToDto(Month month, int depthPermanence, int depthPlanning, int depthSuperviseur) {
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

        if(depthSuperviseur>0){
            if(month.getSuperviseur()!=null){
                monthDto.setSuperviseur(PersonnelController.convertPersonnelToDto(month.getSuperviseur(), 1, 1, 1, 1, 1, depthSuperviseur-1, 1, 0, 0));
            }
        }

        return monthDto;
    }
}
