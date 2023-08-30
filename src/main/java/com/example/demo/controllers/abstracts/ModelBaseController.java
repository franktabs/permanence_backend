package com.example.demo.controllers.abstracts;

import com.example.demo.dto.AbsenceDto;
import com.example.demo.dto.interfaces.ModelDto;
import com.example.demo.entities.Absence;
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
import org.springframework.web.bind.annotation.*;

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
            modelDtos.add(convertModelToDto(model, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
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
        return ResponseEntity.status(HttpStatus.OK).body(convertModelToDto(model, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
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

    @PutMapping(path = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateOne(@Valid @RequestBody D modelDto, BindingResult bindingResult, @PathVariable Long id) {
        try {
            if (bindingResult.hasErrors()) {
                return actionError(bindingResult);
            }
            J model = convertDtoToModel(modelDto);
            System.out.println("\n\n Conversion termninée" + model.toString() + " \n\n");
            model = service.update(model, id);
            if(model==null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(convertModelToDto(model, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1));
        } catch (DataIntegrityViolationException e) {
            return catchMessageDataIntegrity(e);
        } catch (Exception e) {
            return catchMessageException(e);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePersonnelJour(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.ok().body(Map.of("success", "Operation réussi"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("errors", "echec de l'operation"));
        }
    }
}
