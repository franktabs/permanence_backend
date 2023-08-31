package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.PersonnelController;
import com.example.demo.controllers.abstracts.ModelConvertController;
import com.example.demo.dto.DepartementDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.entities.Departement;
import com.example.demo.entities.Personnel;
import com.example.demo.services.DepartementService;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.controllers.PersonnelController.convertPersonnelToDto;
import static com.example.demo.controllers.abstracts.modelConvert.DirectionConvertController.convertDirectionToDto;
import static com.example.demo.controllers.abstracts.modelConvert.DirectionConvertController.convertDtoToDirection;

public class DepartementConvertController extends ModelConvertController<Departement, DepartementDto, DepartementService> {


    public static DepartementDto convertDepartementToDto(Departement departement, int depthDirection, int depthPersonnel) {

        System.out.println("\n\n aprres conversion \n"+departement.toString()+"\nn");

        DepartementDto dto = new DepartementDto(
                departement.getId(),
                departement.getOrganizationId(),
                departement.getLevel(),
                departement.getType(),
                departement.getTreepath(),
                departement.getParentorganizationId(),
                departement.getName()
        );
        if (depthDirection > 0) {
            if (departement.getDirection() != null) {
                dto.setDirection(convertDirectionToDto(departement.getDirection(), depthDirection - 1, 1));
            }

        }
        if (depthPersonnel > 0) {

            Set<PersonnelDto> personnelDtos = new HashSet<>();
            for (Personnel personnel : departement.getPersonnels()) {
                personnelDtos.add(convertPersonnelToDto(personnel, depthPersonnel - 1, 1,1, 1,1, 1, 1, 0, 0));
            }

            dto.setPersonnels(personnelDtos);
        }
        return dto;
    }

    public static Departement convertDtoToDepartement(DepartementDto departementDto) {
        Departement departement = new Departement(
                departementDto.getId(),
                departementDto.getOrganizationId(),
                departementDto.getLevel(),
                departementDto.getType_(),
                departementDto.getTreePath(),
                departementDto.getParentorganizationId(),
                departementDto.getName()
        );
        if (departementDto.getDirection() != null) {
            departement.setDirection(convertDtoToDirection(departementDto.getDirection()));
        }
        Set<Personnel> personnels = new HashSet<>();
        if (departementDto.getPersonnels() != null) {

            for (PersonnelDto personnelDto : departementDto.getPersonnels()) {
                personnels.add(PersonnelController.convertDtoToPersonnel(personnelDto));
            }
        }
        departement.setPersonnels(personnels);
        return departement;
    }

    @Override
    public DepartementDto convertModelToDto(Departement model, int... depth) {
        return convertDepartementToDto(model, depth[0], depth[1]);
    }

    @Override
    public Departement convertDtoToModel(DepartementDto modelDto) {
        return convertDtoToDepartement(modelDto);
    }
}
