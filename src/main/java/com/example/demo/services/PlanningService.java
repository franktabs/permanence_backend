package com.example.demo.services;

import com.example.demo.entities.Month;
import com.example.demo.entities.Planning;
import com.example.demo.repositories.MonthRepository;
import com.example.demo.repositories.PlanningRepository;
import com.example.demo.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanningService extends BaseService<Planning, PlanningRepository> {

}
