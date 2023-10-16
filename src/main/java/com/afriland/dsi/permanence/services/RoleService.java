package com.afriland.dsi.permanence.services;

import com.afriland.dsi.permanence.entities.Month;
import com.afriland.dsi.permanence.entities.Personnel;
import com.afriland.dsi.permanence.entities.Role;
import com.afriland.dsi.permanence.repositories.MonthRepository;
import com.afriland.dsi.permanence.repositories.PersonnelRepository;
import com.afriland.dsi.permanence.repositories.RoleRepository;
import com.afriland.dsi.permanence.services.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService extends BaseService<Role, RoleRepository> {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    PersonnelService personnelService;


    public Personnel giveAllRoles(Long userId){
        Personnel personnel = personnelRepository.findByUserId(userId);
        if(personnel==null){
            return null;
        }
        Set<Role> roleList = new HashSet<>(roleRepository.findAll());
        personnel.getRoles().addAll(roleList) ;
        for (Role role:roleList){
            role.getPersonnels().add(personnel);
            roleRepository.save(role);
        }
        return personnelRepository.findByUserId(userId);

    }

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
        System.out.println("\nVoici Role = "+role+"\n\n Voici personnel = "+personnel);
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
