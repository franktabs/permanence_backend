package com.afriland.dsi.permanence.services.abstracts;

import com.afriland.dsi.permanence.dto.interfaces.ModelDto;
import com.afriland.dsi.permanence.entities.interfaces.Model;
import com.afriland.dsi.permanence.repositories.abstracts.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class BaseService< J extends Model , T extends ModelRepository<J, Long>> implements ModelService<J> {

    @Autowired
    public T repository;


    @Override
    public J create(J model){
        System.out.println("\n\nmodel en enregistrer => "+model );
        if(model==null) return null;
        return repository.save(model);
    }

    @Override
    public J update(J modelUpdate, Long id){
        if(modelUpdate==null || id ==null) return null;
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
