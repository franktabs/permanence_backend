package com.example.demo.services;


import com.example.demo.entities.Notification;
import com.example.demo.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public Notification create(Notification notification){
        return notificationRepository.save(notification);
    }


    public Notification update(Notification notificationUpdate, Long id){
        Notification notification1 = notificationRepository.findById(id).orElse(null);
        if(notification1==null) return null;
        if(!notification1.getId().equals(notificationUpdate.getId())) return null;
        return notificationRepository.save(notificationUpdate);
    }

    public List<Notification> getAllNotification(){
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id){
        return notificationRepository.findById(id).orElse(null);
    }
}
