package com.example.demo.controllers;

import com.example.demo.controllers.abstracts.modelConvert.PersonnelJourConvertController;
import com.example.demo.dto.MonthDto;
import com.example.demo.dto.PersonnelJourDto;
import com.example.demo.dto.PersonnelNuitDto;
import com.example.demo.entities.Month;
import com.example.demo.entities.PersonnelJour;
import com.example.demo.entities.PersonnelNuit;
import com.example.demo.services.MonthService;
import com.example.demo.services.PersonnelJourService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping(path = "personnel_jour")
public class PersonnelJourController extends PersonnelJourConvertController {
}
