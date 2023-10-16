package com.afriland.dsi.permanence.controllers;

import com.afriland.dsi.permanence.controllers.abstracts.modelConvert.AnnonceConvertController;
import com.afriland.dsi.permanence.dto.NotificationDto;
import com.afriland.dsi.permanence.dto.AnnonceDto;
import com.afriland.dsi.permanence.entities.Notification;
import com.afriland.dsi.permanence.entities.Annonce;
import com.afriland.dsi.permanence.services.AnnonceService;
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
@RequestMapping(path = "annonce")
public class AnnonceController extends AnnonceConvertController {}

