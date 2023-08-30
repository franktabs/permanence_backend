package com.example.demo.services.abstracts;

import com.example.demo.entities.interfaces.Model;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseService< J extends Model , T extends ModelRepository<J, Long>> implements ModelService<J> {

    @Autowired
    public T repository;


    @Override
    public J create(J model){
        return repository.save(model);
    }

    @Override
    public J update(J modelUpdate, Long id){
        J model1 = repository.findById(id).orElse(null);
        if(model1==null) return null;
        if(!model1.getId().equals(modelUpdate.getId())) return null;
        return repository.save(modelUpdate);
    }

    @Override
    public List<J> getAllModel() {
        return repository.findAll();
    }


    @Override
    public J getModelById(Long id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Long id){

        if(!repository.existsById(id)){
            return false;
        }

        repository.deleteModel(id);
        return true;
    }


}
