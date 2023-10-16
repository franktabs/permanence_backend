package com.afriland.dsi.permanence.services;

import com.afriland.dsi.permanence.entities.Month;
import com.afriland.dsi.permanence.entities.PersonnelJour;
import com.afriland.dsi.permanence.repositories.MonthRepository;
import com.afriland.dsi.permanence.repositories.PersonnelJourRepository;
import com.afriland.dsi.permanence.services.abstracts.BaseService;
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
