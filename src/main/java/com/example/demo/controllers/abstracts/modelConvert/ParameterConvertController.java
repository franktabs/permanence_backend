package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.DirectionController;
import com.example.demo.controllers.abstracts.ModelConvertController;
import com.example.demo.dto.ParameterDto;
import com.example.demo.entities.Parameter;
import com.example.demo.services.ParameterService;

public class ParameterConvertController extends ModelConvertController<Parameter, ParameterDto, ParameterService> {

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

        return parameterDto;

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

    @Override
    public ParameterDto convertModelToDto(Parameter model, int... depth) {
        return convertParameterToDto(model, depth[0]);
    }

    @Override
    public Parameter convertDtoToModel(ParameterDto modelDto) {
        return convertDtoToParameter(modelDto);
    }
}
