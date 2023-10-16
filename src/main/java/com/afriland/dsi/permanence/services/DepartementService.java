package com.afriland.dsi.permanence.services;

import com.afriland.dsi.permanence.entities.Departement;
import com.afriland.dsi.permanence.entities.Month;
import com.afriland.dsi.permanence.repositories.DepartementRepository;
import com.afriland.dsi.permanence.repositories.MonthRepository;
import com.afriland.dsi.permanence.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementService extends BaseService<Departement, DepartementRepository> {
    @Autowired
    DepartementRepository departementRepository;

    public Departement findMinOrganizationId(){
        return departementRepository.findMinOrganizationId();
    }
}
