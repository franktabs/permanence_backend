package com.example.demo.services;

import com.example.demo.entities.Month;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.Role;
import com.example.demo.repositories.MonthRepository;
import com.example.demo.repositories.PersonnelRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends BaseService<Role, RoleRepository> {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PersonnelRepository personnelRepository;



    public Role deletePersonnel(Long id, Long id2) {
        Role role = roleRepository.findById(id).orElse(null);
        Personnel personnel = personnelRepository.findById(id2).orElse(null);
        if (role == null || personnel == null) {
            return null;
        }
        role.getPersonnels().remove(personnel);
        return roleRepository.save(role);
    }

    public Role addPersonnel(Long id, Long id2) {
        Role role = roleRepository.findById(id).orElse(null);
        Personnel personnel = personnelRepository.findById(id2).orElse(null);
        if (role == null || personnel == null) {
            return null;
        }
        if(role.getPersonnels().contains(personnel)){
            return role;
        }
        role.getPersonnels().add(personnel);
        return roleRepository.save(role);
    }

    public Role addPersonnels(Long id, Role role){
        Role roleInBD = roleRepository.findById(id).orElse(null);
        if (roleInBD == null || role.getId()!=id) {
            return null;
        }
        boolean add = false;
        for(Personnel personnel : role.getPersonnels()){
            Personnel personnelInBD = personnelRepository.findById(personnel.getId()).orElse(null);
            if(personnelInBD!=null && !roleInBD.getPersonnels().contains(personnelInBD)){
                add=true;
                roleInBD.getPersonnels().add(personnelInBD);
            }
        }
        if(add){
            return roleRepository.save(roleInBD);
        }else{
            return roleInBD;
        }
    }

}
