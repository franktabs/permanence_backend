package com.example.demo.controllers;

import com.example.demo.controllers.abstracts.modelConvert.ParameterConvertController;
import com.example.demo.dto.ParameterDto;
import com.example.demo.entities.Parameter;
import com.example.demo.services.ParameterService;
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
@RequestMapping(path = "parameter")
public class ParameterController extends ParameterConvertController {


}
