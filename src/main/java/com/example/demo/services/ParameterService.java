package com.example.demo.services;

import com.example.demo.entities.Parameter;
import com.example.demo.repositories.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterService {

    @Autowired
    ParameterRepository parameterRepository;

    public Parameter create(Parameter parameter){
        return parameterRepository.save(parameter);
    }


    public Parameter update(Parameter parameterUpdate, Long id){
        Parameter parameter1 = parameterRepository.findById(id).orElse(null);
        if(parameter1==null) return null;
        if(!parameter1.getId().equals(parameterUpdate.getId())) return null;
        return parameterRepository.save(parameterUpdate);
    }

    public List<Parameter> getAllParameter(){
        return parameterRepository.findAll();
    }

    public Parameter getParameterById(Long id){
        return parameterRepository.findById(id).orElse(null);
    }
}
