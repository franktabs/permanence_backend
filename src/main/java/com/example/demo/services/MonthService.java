package com.example.demo.services;

import com.example.demo.entities.Month;
import com.example.demo.repositories.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthService {

    @Autowired
    MonthRepository monthRepository;

    public Month create(Month month){
        return monthRepository.save(month);
    }


    public Month update(Month monthUpdate, Long id){
        Month month1 = monthRepository.findById(id).orElse(null);
        if(month1==null) return null;
        if(!month1.getId().equals(monthUpdate.getId())) return null;
        return monthRepository.save(monthUpdate);
    }

    public List<Month> getAllMonth(){
        return monthRepository.findAll();
    }

    public Month getMonthById(Long id){
        return monthRepository.findById(id).orElse(null);
    }
}
