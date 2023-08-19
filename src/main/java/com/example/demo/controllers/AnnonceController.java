package com.example.demo.controllers;

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
public class AnnonceController {

    @Autowired
    AnnonceService annonceService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnnonceDto>> allNotification() {
        List<Annonce> annonces = annonceService.getAllNotification();
        List<AnnonceDto> annonceDtos = new ArrayList<>();
        for (Annonce annonce : annonces) {
            annonceDtos.add(convertAnnonceToDto(annonce, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(annonceDtos);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnnonceDto> getOneNotification(@PathVariable Long id){
        Annonce annonce = annonceService.getNotificationById(id);
        if(annonce ==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertAnnonceToDto(annonce, 1,  1));
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody AnnonceDto annonceDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Annonce annonce = convertDtoToAnnonce(annonceDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertAnnonceToDto(annonceService.create(annonce), 1, 1));

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


    public static AnnonceDto convertAnnonceToDto(Annonce annonce, int depthEmetteur, int depthNotification){
        AnnonceDto annonceDto = new AnnonceDto(
                annonce.getId(),
                annonce.getType(),
                annonce.getMessage(),
                annonce.getSubmissionDate()
        );

        if (depthNotification > 0) {
            Set<NotificationDto> notificationDtos = new HashSet<>();
            if (annonce.getNotifications() != null) {
                for (Notification notification : annonce.getNotifications()) {
                    notificationDtos.add(NotificationController.convertNotificationToDto(notification, 1,  depthNotification - 1));
                }
            }

            annonceDto.setAnnonces(notificationDtos);
        }

        if(depthEmetteur > 0){
            if(annonce.getEmetteur()!=null){
                annonceDto.setEmetteur(PersonnelController.convertPersonnelToDto(annonce.getEmetteur(), 0, 0, 0, 0, 0, 0, 0, depthEmetteur-1, 0));
            }
        }

        return annonceDto;
    }

    public static Annonce convertDtoToAnnonce(AnnonceDto annonceDto) {
        Annonce annonce = new Annonce(
                annonceDto.getId(),
                annonceDto.getType(),
                annonceDto.getMessage(),
                annonceDto.getSubmissionDate()
        );

        Set<Notification> notifications = new HashSet<>();
        if(annonceDto.getAnnonces()!=null){
            for(NotificationDto notificationDto : annonceDto.getAnnonces()){
                notifications.add(NotificationController.convertDtoToNotification(notificationDto));
            }
        }
        annonce.setNotifications(notifications);

        if(annonceDto.getEmetteur()!=null){
            annonce.setEmetteur(PersonnelController.convertDtoToPersonnel(annonceDto.getEmetteur()));
        }

        return annonce;
    }
}
