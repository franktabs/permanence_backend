package com.example.demo.controllers;

import com.example.demo.dto.ParameterDto;
import com.example.demo.entities.Parameter;
import com.example.demo.services.ParameterService;
import com.example.demo.utils.StringExtract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(path = "parameter")
public class ParameterController {

    @Autowired
    ParameterService parameterService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ParameterDto>> allParameter() {
        List<Parameter> parameters = parameterService.getAllParameter();
        List<ParameterDto> parameterDtos = new ArrayList<>();
        for (Parameter parameter : parameters) {
            parameterDtos.add(convertParameterToDto(parameter, 1));
        }
        return ResponseEntity.status(HttpStatus.OK).body(parameterDtos);
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParameterDto> getOneParameter(@PathVariable Long id){
        Parameter parameter = parameterService.getParameterById(id);
        if(parameter ==null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertParameterToDto(parameter, 1));
    }

    @PostMapping()
    public ResponseEntity<?> creer(@Valid @RequestBody ParameterDto parameterDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> mapErrors = new HashMap<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    mapErrors.put(error.getField(), error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(mapErrors);
            }
            Parameter parameter = convertDtoToParameter(parameterDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(convertParameterToDto(parameterService.create(parameter), 1));

        } catch (DataIntegrityViolationException e) {
            Map<String, String> message = StringExtract.keyValueError(e.getMostSpecificCause().getMessage());
            System.out.println("\n\nerreur ici" + message + "\n\n");
            if (message.isEmpty()) {
                message.put("errors", e.getMostSpecificCause().getMessage());
            }
            return ResponseEntity.badRequest().body(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur au niveau du serveur c'est produit");
        }
    }

    public static ParameterDto convertParameterToDto(Parameter parameter, int depthDirection){
        ParameterDto parameterDto = new ParameterDto(
                parameter.getId(),
                parameter.getCode(),
                parameter.getLibelle(),
                parameter.getValeur()
        );

        if(depthDirection > 0){
            if(parameter.getDirection()!=null){
                parameterDto.setDirection(DirectionController.convertDirectionToDto(parameter.getDirection(), 0, depthDirection-1));
            }
        }

        return  parameterDto;

    }

    public static Parameter convertDtoToParameter(ParameterDto parameterDto){
        Parameter parameter = new Parameter(
                parameterDto.getId(),
                parameterDto.getCode(),
                parameterDto.getLibelle(),
                parameterDto.getValeur()
        );

        if(parameterDto.getDirection()!=null){
            parameter.setDirection(DirectionController.convertDtoToDirection(parameterDto.getDirection()));
        }

        return parameter;
    }


}
