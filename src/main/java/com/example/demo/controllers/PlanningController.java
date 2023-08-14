package com.example.demo.controllers;


import com.example.demo.dto.DirectionDto;
import com.example.demo.dto.MonthDto;
import com.example.demo.dto.PlanningDto;
import com.example.demo.entities.Direction;
import com.example.demo.entities.Month;
import com.example.demo.entities.Planning;
import com.example.demo.repositories.PlanningRepository;
import com.example.demo.services.PlanningService;
import com.example.demo.utils.StringExtract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/planning")
public class PlanningController {

    @Autowired
    PlanningService planningService;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> creerOne(@Valid @RequestBody PlanningDto planningDto, BindingResult bindingResult) {

        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Planning planning = convertDtoToPlanning(planningDto);
            System.out.println("\n\n Conversion termninée" + planning.toString() + " \n\n");
            planning = planningService.create(planning);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertPlanningToDto(planning, 1));
        } catch (DataIntegrityViolationException e) {
            Map<String, String> message = StringExtract.keyValueError(e.getMostSpecificCause().getMessage());
            System.out.println("\n\nerreur ici" + message + "\n\n");
            if (message.isEmpty()) {
                message.put("errors", e.getMostSpecificCause().getMessage());
            }
            return ResponseEntity.badRequest().body(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur au niveau du serveur c'est produit");
        }
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlanningDto>> getAll() {
        List<Planning> plannings = planningService.getAllPlanning();
        List<PlanningDto> planningDtos = new ArrayList<>();
        for (Planning planning : plannings) {
            planningDtos.add(convertPlanningToDto(planning, 1));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(planningDtos);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanningDto> getOnePlanning(@PathVariable Long id){
        Planning planning = planningService.getPlanningById(id);
        if(planning==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertPlanningToDto(planning, 1));
    }
    public static PlanningDto convertPlanningToDto(Planning planning, int depthMonth) {
        PlanningDto planningDto = new PlanningDto(
                planning.getId(),
                planning.getStart(),
                planning.getEnd(),
                planning.getPeriode(),
                planning.getIsValid()
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
                planningDto.getIsValid()
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
