package com.example.demo.controllers;

import com.example.demo.controllers.abstracts.modelConvert.AbsenceConvertController;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("absence")
public class AbsenceController extends AbsenceConvertController {
}
