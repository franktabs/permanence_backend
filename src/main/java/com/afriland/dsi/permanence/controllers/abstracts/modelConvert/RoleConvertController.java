package com.afriland.dsi.permanence.controllers.abstracts.modelConvert;

import com.afriland.dsi.permanence.controllers.PersonnelController;
import com.afriland.dsi.permanence.controllers.abstracts.ModelConvertController;
import com.afriland.dsi.permanence.dto.PersonnelDto;
import com.afriland.dsi.permanence.dto.RoleDto;
import com.afriland.dsi.permanence.entities.Personnel;
import com.afriland.dsi.permanence.entities.Role;
import com.afriland.dsi.permanence.services.RoleService;

import java.util.HashSet;
import java.util.Set;

public class RoleConvertController extends ModelConvertController<Role, RoleDto, RoleService> {
    @Override
    public RoleDto convertModelToDto(Role model, int... depth) {
        return convertRoleToDto(model, depth[0]);
    }

    @Override
    public Role convertDtoToModel(RoleDto modelDto) {
        return convertDtoToRole(modelDto);
    }


    public static RoleDto convertRoleToDto(Role role, int depthPersonnel) {
        RoleDto roleDto = new RoleDto(
                role.getId(),
                role.getName()
        );

        if (depthPersonnel > 0) {
            Set<PersonnelDto> personnelDtos = new HashSet<>();
            if (role.getPersonnels() != null) {
                for (Personnel personnel : role.getPersonnels()) {
                    personnelDtos.add(PersonnelController.convertPersonnelToDto(personnel, 0, 0, 0, 0, 0, 0, depthPersonnel-1, 0, 0));
                }
            }

            roleDto.setPersonnels(personnelDtos);
        }

        return roleDto;
    }

    public static Role convertDtoToRole(RoleDto roleDto){
        Role role = new Role(
                roleDto.getId(),
                roleDto.getName()
        );

        Set<Personnel> personnels = new HashSet<>();
        if(roleDto.getPersonnels()!=null){
            for(PersonnelDto personnelDto : roleDto.getPersonnels()){
                personnels.add(PersonnelController.convertDtoToPersonnel(personnelDto));
            }
        }
        role.setPersonnels(personnels);

        return role;
    }
}
