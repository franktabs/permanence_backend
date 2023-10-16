package com.example.demo.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.Map;

public class ValidationEntities<T> {

    @Autowired
    Validator validator;

    public Map<String,String> objectErrors(T entities){

        Map<String, String> mapErrors = new HashMap<>();
        Errors errors = new BeanPropertyBindingResult(entities, "direction");
        validator.validate(entities, errors);
        if(errors.hasErrors()){
            for(FieldError error: errors.getFieldErrors()){
                mapErrors.put(error.getField(),error.getDefaultMessage());
            }
        }
        return mapErrors;
    }



}
