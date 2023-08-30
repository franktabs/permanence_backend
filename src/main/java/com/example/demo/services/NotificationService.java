package com.example.demo.services;

import com.example.demo.entities.Month;
import com.example.demo.entities.Notification;
import com.example.demo.repositories.MonthRepository;
import com.example.demo.repositories.NotificationRepository;
import com.example.demo.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService extends BaseService<Notification, NotificationRepository> {

}
