package com.afriland.dsi.permanence.controllers;

import com.afriland.dsi.permanence.controllers.abstracts.modelConvert.NotificationConvertController;
import com.afriland.dsi.permanence.dto.NotificationDto;
import com.afriland.dsi.permanence.entities.Notification;
import com.afriland.dsi.permanence.services.NotificationService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "notification")
public class NotificationController extends NotificationConvertController {

}
