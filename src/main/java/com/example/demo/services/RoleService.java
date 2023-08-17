package com.example.demo.services;

import com.example.demo.entities.Month;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.Role;
import com.example.demo.repositories.PersonnelRepository;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PersonnelRepository personnelRepository;

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id){
        return roleRepository.findById(id).orElse(null);
    }

    public Role create(Role role){
        return roleRepository.save(role);
    }


    public Role deletePersonnel(Long id, Long id2){
        Role role = roleRepository.findById(id).orElse(null);
        Personnel personnel = personnelRepository.findById(id2).orElse(null);
        if(role==null || personnel==null){
            return null;
        }
        role.getPersonnels().remove(personnel);
        return roleRepository.save(role);
    }
}
