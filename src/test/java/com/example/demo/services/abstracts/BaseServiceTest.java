package com.example.demo.services.abstracts;

import com.example.demo.entities.interfaces.Model;
import com.example.demo.repositories.abstracts.ModelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class BaseServiceTest<J extends Model, T extends ModelService<J>> extends ModelServiceTest<J, T> {


    public J model;


    public abstract J modifyModel(J model);


    @Override
    public J create() {

        J modelSave = service.create(null);
        Assertions.assertNull(modelSave);


        modelSave = service.create(model);
        Assertions.assertNotNull(modelSave);
        Assertions.assertNotNull(modelSave.getId());
        return modelSave;
    }

    @Override
    public J update() {

        /*First condition*/
        J modelSave = service.update(null,null);
        Assertions.assertNull(modelSave);

        model = service.create(model);
        System.out.println(model);
        J modelUpdate = modifyModel(model);
        System.out.println(modelUpdate);
        /*Bad id given*/
        Long id = modelUpdate.getId();
        modelSave = service.update(modelUpdate, -1l);
        Assertions.assertNull(modelSave);

        modelSave = service.update(modelUpdate, id+1);
        Assertions.assertNull(modelSave);

        /*Good id given*/
        modelSave = service.update(modelUpdate, id);
        Assertions.assertNotNull(modelSave);
        Assertions.assertEquals(id, modelSave.getId());
        Assertions.assertEquals(modelUpdate, modelSave);

        return modelSave;
    }

    @Override
    public List<J> getAllModel() {
        return null;
    }

    @Override
    public J getModelById() {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }
}