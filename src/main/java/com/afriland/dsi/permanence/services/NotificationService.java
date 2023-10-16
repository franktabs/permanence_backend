package com.afriland.dsi.permanence.services;

import com.afriland.dsi.permanence.entities.Month;
import com.afriland.dsi.permanence.entities.Notification;
import com.afriland.dsi.permanence.repositories.MonthRepository;
import com.afriland.dsi.permanence.repositories.NotificationRepository;
import com.afriland.dsi.permanence.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService extends BaseService<Notification, NotificationRepository> {

}
