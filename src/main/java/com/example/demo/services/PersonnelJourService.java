package com.example.demo.services;

import com.example.demo.entities.Month;
import com.example.demo.entities.PersonnelJour;
import com.example.demo.repositories.MonthRepository;
import com.example.demo.repositories.PersonnelJourRepository;
import com.example.demo.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PersonnelJourService extends BaseService<PersonnelJour, PersonnelJourRepository> {

}
