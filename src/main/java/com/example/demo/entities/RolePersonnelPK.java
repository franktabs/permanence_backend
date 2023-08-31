package com.example.demo.entities;

import java.io.Serializable;
import java.util.Objects;

public class RolePersonnelPK implements Serializable {

    private Long role;
    private Long personnel;

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Long personnel) {
        this.personnel = personnel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePersonnelPK that = (RolePersonnelPK) o;
        return Objects.equals(role, that.role) && Objects.equals(personnel, that.personnel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, personnel);
    }
}
