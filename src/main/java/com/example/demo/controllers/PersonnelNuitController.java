package com.example.demo.controllers;

import com.example.demo.controllers.abstracts.modelConvert.PersonnelNuitConvertController;
import com.example.demo.dto.MonthDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.dto.PersonnelNuitDto;
import com.example.demo.entities.Month;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.PersonnelNuit;
import com.example.demo.services.MonthService;
import com.example.demo.services.PersonnelNuitService;
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
@RequestMapping(path = "personnel_nuit")
public class PersonnelNuitController extends PersonnelNuitConvertController {

}
