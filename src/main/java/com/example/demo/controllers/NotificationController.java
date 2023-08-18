package com.example.demo.controllers;

import com.example.demo.dto.AnnonceDto;
import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.PermanenceDto;
import com.example.demo.entities.Annonce;
import com.example.demo.entities.Notification;
import com.example.demo.entities.Notification;
import com.example.demo.entities.Permanence;
import com.example.demo.services.NotificationService;
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
@RequestMapping(path = "notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NotificationDto>> allNotification() {
        List<Notification> notifications = notificationService.getAllNotification();
        List<NotificationDto> notificationDtos = new ArrayList<>();
        for (Notification notification : notifications) {
            notificationDtos.add(convertNotificationToDto(notification, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(notificationDtos);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NotificationDto> getOneNotification(@PathVariable Long id){
        Notification notification = notificationService.getNotificationById(id);
        if(notification==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertNotificationToDto(notification, 1,  1));
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody NotificationDto notificationDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Notification notification = convertDtoToNotification(notificationDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertNotificationToDto(notificationService.create(notification), 1, 1));

        } catch (DataIntegrityViolationException e) {
            Map<String, String> message = StringExtract.keyValueError(e.getMostSpecificCause().getMessage());
            System.out.println("\n\nerreur ici" + message + "\n\n");
            if (message.isEmpty()) {
                message.put("errors", e.getMostSpecificCause().getMessage());
            }
            return ResponseEntity.badRequest().body(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur au niveau du serveur c'est produit");
        }
    }


    public static NotificationDto convertNotificationToDto(Notification notification, int depthEmetteur, int depthAnnonce){
        NotificationDto notificationDto = new NotificationDto(
                notification.getId(),
                notification.getType(),
                notification.getMessage(),
                notification.getSubmissionDate()
        );

        if (depthAnnonce > 0) {
            Set<AnnonceDto> annonceDtos = new HashSet<>();
            if (notification.getAnnonces() != null) {
                for (Annonce annonce : notification.getAnnonces()) {
                    annonceDtos.add(AnnonceController.convertAnnonceToDto(annonce, 1,  depthAnnonce - 1));
                }
            }

            notificationDto.setAnnonces(annonceDtos);
        }

        if(depthEmetteur > 0){
            if(notification.getEmetteur()!=null){
                notificationDto.setEmetteur(PersonnelController.convertPersonnelToDto(notification.getEmetteur(), 0, 0, 0, 0, 0, 0, 0, depthEmetteur-1, 0));
            }
        }

        return notificationDto;
    }

    public static Notification convertDtoToNotification(NotificationDto notificationDto) {
        Notification notification = new Notification(
                notificationDto.getId(),
                notificationDto.getType(),
                notificationDto.getMessage(),
                notificationDto.getSubmissionDate()
        );

        Set<Annonce> annonces = new HashSet<>();
        if(notificationDto.getAnnonces()!=null){
            for(AnnonceDto annonceDto : notificationDto.getAnnonces()){
                annonces.add(AnnonceController.convertDtoToAnnonce(annonceDto));
            }
        }
        notification.setAnnonces(annonces);

        if(notificationDto.getEmetteur()!=null){
            notification.setEmetteur(PersonnelController.convertDtoToPersonnel(notificationDto.getEmetteur()));
        }

        return notification;
    }
}
