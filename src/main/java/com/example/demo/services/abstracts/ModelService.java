package com.example.demo.services.abstracts;

import com.example.demo.entities.interfaces.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface  ModelService<J extends Model> {

    public J create(J model);

    public J update(J modelUpdate, Long id);

    public List<J> getAllModel();

    public J getModelById(Long id);

    public boolean delete(Long id);

}
