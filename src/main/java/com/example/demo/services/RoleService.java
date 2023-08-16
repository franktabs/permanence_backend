package com.example.demo.services;

import com.example.demo.entities.Month;
import com.example.demo.entities.Role;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id){
        return roleRepository.findById(id).orElse(null);
    }

    public Role create(Role role){
        return roleRepository.save(role);
    }

}
