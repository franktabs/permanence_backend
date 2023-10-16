package com.afriland.dsi.permanence.controllers;


import com.afriland.dsi.permanence.controllers.abstracts.modelConvert.PlanningConvertController;
import com.afriland.dsi.permanence.dto.DirectionDto;
import com.afriland.dsi.permanence.dto.MonthDto;
import com.afriland.dsi.permanence.dto.PlanningDto;
import com.afriland.dsi.permanence.entities.Direction;
import com.afriland.dsi.permanence.entities.Month;
import com.afriland.dsi.permanence.entities.Planning;
import com.afriland.dsi.permanence.entities.Role;
import com.afriland.dsi.permanence.repositories.PlanningRepository;
import com.afriland.dsi.permanence.services.PlanningService;
import com.afriland.dsi.permanence.utils.StringExtract;
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
@RequestMapping(path = "planning")
public class PlanningController extends PlanningConvertController {

    @Autowired
    PlanningService planningService;


    @GetMapping(path = "personnel/{id}")
    public ResponseEntity<Set<PlanningDto>> getPersonnelPlanning(@PathVariable Long id) {
        Set<PlanningDto> planningDtoList = planningService.findPlanningByPersonnelId(id);
        if (planningDtoList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(planningDtoList);
    }

    @GetMapping(path = "count-personnels/{id}")
    public ResponseEntity<Map<Long, Long>> getCountPersonnelsPlanning(@PathVariable Long id){
        Map<Long, Long> objects = planningService.countPersonnelsPlanning(id);
        if(objects==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(objects);
    }
}
