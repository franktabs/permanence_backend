package com.afriland.dsi.permanence.controllers.abstracts.modelConvert;

import com.afriland.dsi.permanence.controllers.abstracts.ModelConvertController;
import com.afriland.dsi.permanence.dto.DepartementDto;
import com.afriland.dsi.permanence.dto.DirectionDto;
import com.afriland.dsi.permanence.dto.ParameterDto;
import com.afriland.dsi.permanence.entities.Departement;
import com.afriland.dsi.permanence.entities.Direction;
import com.afriland.dsi.permanence.entities.Parameter;
import com.afriland.dsi.permanence.services.DirectionService;

import java.util.HashSet;
import java.util.Set;

import static com.afriland.dsi.permanence.controllers.DepartementController.convertDepartementToDto;
import static com.afriland.dsi.permanence.controllers.DepartementController.convertDtoToDepartement;
import static com.afriland.dsi.permanence.controllers.ParameterController.convertParameterToDto;

public class DirectionConvertController extends ModelConvertController<Direction, DirectionDto, DirectionService> {

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
