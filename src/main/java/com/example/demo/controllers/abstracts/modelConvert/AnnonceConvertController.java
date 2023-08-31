package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.NotificationController;
import com.example.demo.controllers.PersonnelController;
import com.example.demo.controllers.abstracts.ModelConvertController;
import com.example.demo.dto.AnnonceDto;
import com.example.demo.dto.NotificationDto;
import com.example.demo.entities.Annonce;
import com.example.demo.entities.Notification;
import com.example.demo.services.AnnonceService;

import java.util.HashSet;
import java.util.Set;

public class AnnonceConvertController extends ModelConvertController<Annonce, AnnonceDto, AnnonceService> {


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

    @Override
    public AnnonceDto convertModelToDto(Annonce model, int... depth) {
        return convertAnnonceToDto(model, depth[0], depth[1]);
    }

    @Override
    public Annonce convertDtoToModel(AnnonceDto modelDto) {
        return convertDtoToAnnonce(modelDto);
    }
}
