package com.example.demo.controllers;


import com.example.demo.controllers.abstracts.modelConvert.PlanningConvertController;
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
@CrossOrigin()
@RequestMapping("/planning")
public class PlanningController extends PlanningConvertController {

}
