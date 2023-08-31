package com.example.demo.controllers.abstracts;

import com.example.demo.dto.PersonnelDto;
import com.example.demo.dto.interfaces.ModelDto;
import com.example.demo.entities.interfaces.Model;
import com.example.demo.enumeration.Config;
import com.example.demo.services.abstracts.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public abstract class ModelController<J extends Model, D extends ModelDto, T extends ModelService<J> > extends ModelMessageController<D> {
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


    @Override
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
    public ResponseEntity<?> insert(@Valid @RequestBody D modelDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return actionError(bindingResult);
            }
            System.out.println("\n\nAvant sauvegarde actionSuccess =>"+modelDto);
            return actionSuccess(modelDto);

        } catch (DataIntegrityViolationException e) {
            return catchMessageDataIntegrity(e);
        } catch (Exception e) {
            return catchMessageException(e);
        }
    }

    @PostMapping(path = "/many")
    public ResponseEntity<?>  insertMany(@Valid @RequestBody List<D> modelDtos, BindingResult bindingResult){
        try{

            if (bindingResult.hasErrors()) {
                return actionError(bindingResult);
            }
            List<D> modelDtosSave = new ArrayList<>();

            for (D modelDto:modelDtos){
                J model = convertDtoToModel(modelDto);
                model = service.create(model);
                if(model!=null && model.getId()!=null){
                    D modelToDto = convertModelToDto(model, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
                    modelDtosSave.add(modelToDto);
                }
            }

            return ResponseEntity.ok().body(modelDtosSave);
        }
        catch (DataIntegrityViolationException e){
            return catchMessageDataIntegrity(e);
        }
        catch (Exception e){
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
