package com.example.demo.services;

import com.example.demo.entities.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseService<T extends JpaRepository<Model, Long> > {
    @Autowired
    T repository;

    public Model create(Model model){
        return repository.save(model);
    }


    public Model update(Model modelUpdate, Long id){
        Model model1 = repository.findById(id).orElse(null);
        if(model1==null) return null;
        if(!model1.getId().equals(modelUpdate.getId())) return null;
        return repository.save(modelUpdate);
    }

    public List<Model> getAllModel(){
        return repository.findAll();
    }

    public Model getBaseServiceById(Long id){
        return repository.findById(id).orElse(null);
    }
}
