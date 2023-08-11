package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.example.demo.entities.Direction}
 */
public class DirectionDto implements Serializable {
    private  Long id;
    private  Long organizationId;
    private  Long level;
    @Size(max = 45)
    private  String type;
    @Size(max = 255)
    private  String treepath;
    private  Integer parentorganizationId;
    @NotNull
    @Size(max = 255)
    private  String name;
    private  Set<DepartementDto> departements;

    public DirectionDto(Long id, Long organizationId, Long level, String type, String treepath, Integer parentorganizationId, @NotNull String name, Set<DepartementDto> departements) {
        this(id,organizationId,level,type,treepath,parentorganizationId,name);
        this.departements = departements;
    }

    public DirectionDto(Long id, Long organizationId, Long level, String type, String treepath, Integer parentorganizationId, @NotNull String name) {
        this.id = id;
        this.organizationId = organizationId;
        this.level = level;
        this.type = type;
        this.treepath = treepath;
        this.parentorganizationId = parentorganizationId;
        this.name = name;
    }

    public void setDepartements(Set<DepartementDto> departements) {
        this.departements = departements;
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

    public Set<DepartementDto> getDepartements() {
        return departements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectionDto entity = (DirectionDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.organizationId, entity.organizationId) &&
                Objects.equals(this.level, entity.level) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.treepath, entity.treepath) &&
                Objects.equals(this.parentorganizationId, entity.parentorganizationId) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.departements, entity.departements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizationId, level, type, treepath, parentorganizationId, name, departements);
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
                "departements = " + departements + ")";
    }
}