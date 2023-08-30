package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.AnnonceController;
import com.example.demo.controllers.PersonnelController;
import com.example.demo.controllers.abstracts.ModelBaseConvertController;
import com.example.demo.dto.NotificationDto;
import com.example.demo.entities.Notification;
import com.example.demo.services.NotificationService;

public class NotificationConvertController extends ModelBaseConvertController<Notification, NotificationDto, NotificationService> {



    public static NotificationDto convertNotificationToDto(Notification notification, int depthRecepteur, int depthAnnonce){

        NotificationDto notificationDto = new NotificationDto(
                notification.getId(),
                notification.getIsViewed(),
                notification.getIsDeleted()
        );

        if(depthRecepteur > 0){
            if(notification.getRecepteur()!=null){
                notificationDto.setRecepteur(PersonnelController.convertPersonnelToDto(notification.getRecepteur(), 0, 0, 0, 0, 0, 0, 0, 0, depthRecepteur-1));
            }
        }

        if(depthAnnonce>0){
            if(notification.getAnnonce()!=null){
                notificationDto.setAnnonce(AnnonceController.convertAnnonceToDto(notification.getAnnonce(), 1, depthAnnonce-1));
            }
        }

        return notificationDto;
    }


    public static Notification convertDtoToNotification(NotificationDto notificationDto){
        Notification notification = new Notification(
                notificationDto.getId(),
                notificationDto.getIsViewed(),
                notificationDto.getIsDeleted()
        );

        if(notificationDto.getRecepteur()!=null){
            notification.setRecepteur(PersonnelController.convertDtoToPersonnel(notificationDto.getRecepteur()));
        }

        if(notificationDto.getAnnonce()!=null){
            notification.setAnnonce(AnnonceController.convertDtoToAnnonce(notificationDto.getAnnonce()));
        }

        return notification;

    }

    @Override
    public NotificationDto convertModelToDto(Notification model, int... depth) {
        return convertNotificationToDto(model, depth[0], depth[1]);
    }

    @Override
    public Notification convertDtoToModel(NotificationDto modelDto) {
        return convertDtoToNotification(modelDto);
    }
}
