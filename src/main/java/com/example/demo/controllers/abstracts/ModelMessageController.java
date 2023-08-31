package com.example.demo.controllers.abstracts;

import com.example.demo.dto.interfaces.ModelDto;
import com.example.demo.utils.StringExtract;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public abstract class ModelMessageController<D extends ModelDto> {

    public ResponseEntity<Map<String, String>> catchMessageDataIntegrity(DataIntegrityViolationException e){
        Map<String, String> message = StringExtract.keyValueError(e.getMostSpecificCause().getMessage());
        System.out.println("\n\nerreur ici" + message + "\n\n");
        if (message.isEmpty()) {
            message.put("errors", e.getMostSpecificCause().getMessage());
        }
        return ResponseEntity.badRequest().body(message);
    }
    public ResponseEntity<Map<String, String>> catchMessageException(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Une erreur au niveau du serveur s'est produit"));
    }

    public ResponseEntity<Map<String, String>> actionError(BindingResult bindingResult){
        Map<String, String> mapErrors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            mapErrors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(mapErrors);
    }

    public abstract ResponseEntity<D> actionSuccess(D modelDto);
}
