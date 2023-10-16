package com.afriland.dsi.permanence.controllers;

import com.afriland.dsi.permanence.controllers.abstracts.modelConvert.PersonnelNuitConvertController;
import com.afriland.dsi.permanence.dto.MonthDto;
import com.afriland.dsi.permanence.dto.PersonnelDto;
import com.afriland.dsi.permanence.dto.PersonnelNuitDto;
import com.afriland.dsi.permanence.entities.Month;
import com.afriland.dsi.permanence.entities.Personnel;
import com.afriland.dsi.permanence.entities.PersonnelNuit;
import com.afriland.dsi.permanence.services.MonthService;
import com.afriland.dsi.permanence.services.PersonnelNuitService;
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
@RequestMapping(path = "personnel_nuit")
public class PersonnelNuitController extends PersonnelNuitConvertController {

}
