package com.example.demo.controllers.abstracts;

import com.example.demo.dto.interfaces.ModelDto;
import com.example.demo.entities.interfaces.Model;
import com.example.demo.services.abstracts.ModelService;
import com.example.demo.utils.StringExtract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public abstract class ModelBaseController<J extends Model, D extends ModelDto, T extends ModelService<J> > extends ModelController {
    @Autowired
    public T service;

    public abstract D convertModelToDto(J model, int ...depth);

    public abstract J convertDtoToModel(D modelDto);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<D>> allModel() {

        List<J> models = service.getAllModel();
        List<D> modelDtos = new ArrayList<>();
        for (J model : models) {
            modelDtos.add(convertModelToDto(model, 1, 1, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(modelDtos);
    }



    public ResponseEntity<D> actionSuccess(D modelDto){
        J model = convertDtoToModel(modelDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertModelToDto(service.create(model), 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
    }





    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<D> getOneModel(@PathVariable Long id){
        J model = service.getModelById(id);
        if(model==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertModelToDto(model, 1, 1, 1));
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody D modelDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return actionError(bindingResult);
            }
            return actionSuccess(modelDto);

        } catch (DataIntegrityViolationException e) {
            return catchMessageDataIntegrity(e);
        } catch (Exception e) {
            return catchMessageException(e);
        }
    }
}
