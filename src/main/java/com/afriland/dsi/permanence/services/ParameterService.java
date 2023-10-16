package com.afriland.dsi.permanence.services;

import com.afriland.dsi.permanence.entities.Month;
import com.afriland.dsi.permanence.entities.Parameter;
import com.afriland.dsi.permanence.repositories.MonthRepository;
import com.afriland.dsi.permanence.repositories.ParameterRepository;
import com.afriland.dsi.permanence.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterService extends BaseService<Parameter, ParameterRepository> {

}
