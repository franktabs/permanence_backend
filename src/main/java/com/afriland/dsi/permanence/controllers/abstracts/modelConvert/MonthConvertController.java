package com.afriland.dsi.permanence.controllers.abstracts.modelConvert;

import com.afriland.dsi.permanence.controllers.PermanenceController;
import com.afriland.dsi.permanence.controllers.PersonnelController;
import com.afriland.dsi.permanence.controllers.PlanningController;
import com.afriland.dsi.permanence.controllers.abstracts.ModelConvertController;
import com.afriland.dsi.permanence.dto.MonthDto;
import com.afriland.dsi.permanence.dto.PermanenceDto;
import com.afriland.dsi.permanence.entities.Month;
import com.afriland.dsi.permanence.entities.Permanence;
import com.afriland.dsi.permanence.services.MonthService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service()
public class MonthConvertController extends ModelConvertController<Month, MonthDto, MonthService> {

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
                month.getFin()
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
