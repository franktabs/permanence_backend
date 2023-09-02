package com.example.demo.services;

import com.example.demo.entities.Role;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.services.abstracts.BaseServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class RoleServiceTest extends BaseServiceTest<Role, RoleService> {


    @Override
    public Role modifyModel(Role model) {
        model.setName("ROLE DE TEST UPDATE MODIFIED");
        return model;
    }

    @Override
    @Test
    public void testCreate() {
        this.model = new Role(null, "ROLE DE TEST");
        Role roleSave = super.create();
        Assertions.assertEquals(this.model.getName(), roleSave.getName());
    }

    @Override
    @Test
    public void testUpdate() {
        this.model = new Role(null, "ROLE DE TEST UPDATE");
        super.update();
    }

    @Override
    @Test
    public void testGetAllModel() {

    }

    @Override
    @Test
    public void testGetModelById() {

    }

    @Override
    @Test
    public void testDelete() {

    }
}