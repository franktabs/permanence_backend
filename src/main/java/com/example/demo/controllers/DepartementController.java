package com.example.demo.controllers;

import com.example.demo.controllers.abstracts.modelConvert.DepartementConvertController;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "departement")
public class DepartementController extends DepartementConvertController {

}
