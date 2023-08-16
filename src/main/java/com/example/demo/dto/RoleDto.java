package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.example.demo.entities.Role}
 */
public class RoleDto implements Serializable {
    private Long id;
    @NotNull
    @Size(max = 255)
    private String name;
    private Set<PersonnelDto> personnels = new LinkedHashSet<>();

    public RoleDto() {
    }

    public RoleDto(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public RoleDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleDto setName(String name) {
        this.name = name;
        return this;
    }

    public Set<PersonnelDto> getPersonnels() {
        return personnels;
    }

    public RoleDto setPersonnels(Set<PersonnelDto> personnels) {
        this.personnels = personnels;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto entity = (RoleDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.personnels, entity.personnels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, personnels);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "personnels = " + personnels + ")";
    }
}