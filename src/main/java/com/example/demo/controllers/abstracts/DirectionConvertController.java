package com.example.demo.controllers.abstracts;

import com.example.demo.dto.DepartementDto;
import com.example.demo.dto.DirectionDto;
import com.example.demo.dto.ParameterDto;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Direction;
import com.example.demo.entities.Parameter;
import com.example.demo.services.DirectionService;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.controllers.DepartementController.convertDepartementToDto;
import static com.example.demo.controllers.DepartementController.convertDtoToDepartement;
import static com.example.demo.controllers.ParameterController.convertParameterToDto;

public class DirectionConvertController extends ModelBaseConvertController<Direction, DirectionDto, DirectionService> {

    @Override
    public Direction convertDtoToModel(DirectionDto modelDto) {
        return convertDtoToDirection(modelDto);
    }

    @Override
    public DirectionDto convertModelToDto(Direction model, int... depth) {
        return convertDirectionToDto(model, depth[0], depth[1]);
    }

    public static DirectionDto convertDirectionToDto(Direction direction, int depthDepartement, int depthParameter) {
        DirectionDto directionDto = new DirectionDto(
                direction.getId(),
                direction.getOrganizationId(),
                direction.getLevel(),
                direction.getType(),
                direction.getTreepath(),
                direction.getParentorganizationId(),
                direction.getName()

        );
        if (depthDepartement > 0) {

            Set<DepartementDto> departementDto = new HashSet<>();
            for (Departement departement : direction.getDepartements()) {
                departementDto.add(convertDepartementToDto(departement, depthDepartement - 1, 1));
            }

            directionDto.setDepartements(departementDto);
        }

        if (depthParameter > 0) {

            Set<ParameterDto> parameterDto = new HashSet<>();
            for (Parameter parameter : direction.getParameters()) {
                parameterDto.add(convertParameterToDto(parameter, depthParameter - 1));
            }

            directionDto.setParameters(parameterDto);
        }

        return directionDto;


    }


    public static Direction convertDtoToDirection(DirectionDto directionDto) {
        Direction direction = new Direction(
                directionDto.getId(),
                directionDto.getOrganizationId(),
                directionDto.getLevel(),
                directionDto.getType_(),
                directionDto.getTreePath(),
                directionDto.getParentorganizationId(),
                directionDto.getName()

        );
        Set<Departement> departements = new HashSet<>();
        if(directionDto.getDepartements()!=null){
            for (DepartementDto departementDto : directionDto.getDepartements()) {
                departements.add(convertDtoToDepartement(departementDto));
            }
        }
        direction.setDepartements(departements);
        return direction;
    }
}
