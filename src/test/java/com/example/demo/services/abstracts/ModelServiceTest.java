package com.example.demo.services.abstracts;

import com.example.demo.entities.interfaces.Model;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ModelServiceTest<J extends Model, T extends ModelService<J>> {


    @Autowired
    public T service;

    public  abstract void testCreate();

    public abstract  void testUpdate();

    public abstract void testGetAllModel();

    public abstract void testGetModelById();

    public abstract  void testDelete();
    public abstract J create() ;

    public abstract J update() ;

    public abstract List<J> getAllModel();

    public abstract J getModelById();

    public abstract boolean delete();
}