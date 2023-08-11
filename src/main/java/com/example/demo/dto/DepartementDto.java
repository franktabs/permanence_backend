package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.example.demo.entities.Departement}
 */
public class DepartementDto implements Serializable {
    private Long id;
    private Long organizationId;
    private Long level;
    @Size(max = 45)
    private String type;
    @Size(max = 255)
    private String treepath;
    private Integer parentorganizationId;
    @NotNull
    @Size(max = 255)
    private String name;
    @NotNull
    private DirectionDto direction;
    private Set<PersonnelDto> personnels;

    public DepartementDto(Long id, Long organizationId, Long level, String type, String treepath, Integer parentorganizationId, @NotNull String name) {
        this.id = id;
        this.organizationId = organizationId;
        this.level = level;
        this.type = type;
        this.treepath = treepath;
        this.parentorganizationId = parentorganizationId;
        this.name = name;
    }

    public DepartementDto(Long id, Long organizationId, Long level, String type, String treepath, Integer parentorganizationId, @NotNull String name, @NotNull DirectionDto direction) {
        this.id = id;
        this.organizationId = organizationId;
        this.level = level;
        this.type = type;
        this.treepath = treepath;
        this.parentorganizationId = parentorganizationId;
        this.name = name;
        this.direction = direction;
    }

    public DepartementDto(Long id, Long organizationId, Long level, String type, String treepath, Integer parentorganizationId, @NotNull String name, Set<PersonnelDto> personnels) {
        this.id = id;
        this.organizationId = organizationId;
        this.level = level;
        this.type = type;
        this.treepath = treepath;
        this.parentorganizationId = parentorganizationId;
        this.name = name;
        this.personnels = personnels;
    }


    public DepartementDto(Long id, Long organizationId, Long level, String type, String treepath, Integer parentorganizationId, @NotNull String name, @NotNull DirectionDto direction, Set<PersonnelDto> personnels) {
        this.id = id;
        this.organizationId = organizationId;
        this.level = level;
        this.type = type;
        this.treepath = treepath;
        this.parentorganizationId = parentorganizationId;
        this.name = name;
        this.direction = direction;
        this.personnels = personnels;
    }

    public Long getId() {
        return id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public Long getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }

    public String getTreepath() {
        return treepath;
    }

    public Integer getParentorganizationId() {
        return parentorganizationId;
    }

    public String getName() {
        return name;
    }

    public DirectionDto getDirection() {
        return direction;
    }

    public Set<PersonnelDto> getPersonnels() {
        return personnels;
    }

    public void setDirection(DirectionDto direction) {
        this.direction = direction;
    }

    public void setPersonnels(Set<PersonnelDto> personnels) {
        this.personnels = personnels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartementDto entity = (DepartementDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.organizationId, entity.organizationId) &&
                Objects.equals(this.level, entity.level) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.treepath, entity.treepath) &&
                Objects.equals(this.parentorganizationId, entity.parentorganizationId) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.direction, entity.direction) &&
                Objects.equals(this.personnels, entity.personnels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizationId, level, type, treepath, parentorganizationId, name, direction, personnels);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "organizationId = " + organizationId + ", " +
                "level = " + level + ", " +
                "type = " + type + ", " +
                "treepath = " + treepath + ", " +
                "parentorganizationId = " + parentorganizationId + ", " +
                "name = " + name + ", " +
                "direction = " + direction + ", " +
                "personnels = " + personnels + ")";
    }
}