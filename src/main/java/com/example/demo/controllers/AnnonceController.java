package com.example.demo.controllers;

import com.example.demo.controllers.abstracts.modelConvert.AnnonceConvertController;
import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.AnnonceDto;
import com.example.demo.entities.Notification;
import com.example.demo.entities.Annonce;
import com.example.demo.services.AnnonceService;
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
@RequestMapping(path = "annonce")
public class AnnonceController extends AnnonceConvertController {}

