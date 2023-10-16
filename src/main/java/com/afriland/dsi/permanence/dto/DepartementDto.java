package com.afriland.dsi.permanence.dto;

import com.afriland.dsi.permanence.dto.interfaces.OrganisationDto;
/*import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.afriland.dsi.permanence.entities.Departement}
 */
public class DepartementDto implements Serializable, OrganisationDto {
    private Long id;
    private Long organizationId;
    private Long level;
    @Size(max = 45)
    private String type_;
    @Size(max = 255)
    private String treePath;
    private Long parentorganizationId;
    @NotNull
    @Size(max = 255)
    private String name;
    @NotNull
    private DirectionDto direction;
    private Set<PersonnelDto> personnels;

    public DepartementDto(Long id, Long organizationId, Long level, String type_, String treePath, Long parentorganizationId, @NotNull String name) {
        this.id = id;
        this.organizationId = organizationId;
        this.level = level;
        this.type_ = type_;
        this.treePath = treePath;
        this.parentorganizationId = parentorganizationId;
        this.name = name;
    }

    public DepartementDto(Long id, Long organizationId, Long level, String type_, String treePath, Long parentorganizationId, @NotNull String name, @NotNull DirectionDto direction) {
        this.id = id;
        this.organizationId = organizationId;
        this.level = level;
        this.type_ = type_;
        this.treePath = treePath;
        this.parentorganizationId = parentorganizationId;
        this.name = name;
        this.direction = direction;
    }

    public DepartementDto(Long id, Long organizationId, Long level, String type_, String treePath, Long parentorganizationId, @NotNull String name, Set<PersonnelDto> personnels) {
        this.id = id;
        this.organizationId = organizationId;
        this.level = level;
        this.type_ = type_;
        this.treePath = treePath;
        this.parentorganizationId = parentorganizationId;
        this.name = name;
        this.personnels = personnels;
    }

    public DepartementDto(){}

    public DepartementDto(Long id, Long organizationId, Long level, String type_, String treePath, Long parentorganizationId, @NotNull String name, @NotNull DirectionDto direction, Set<PersonnelDto> personnels) {
        this.id = id;
        this.organizationId = organizationId;
        this.level = level;
        this.type_ = type_;
        this.treePath = treePath;
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

    public String getType_() {
        return type_;
    }

    public String getTreePath() {
        return treePath;
    }

    public Long getParentorganizationId() {
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
                Objects.equals(this.type_, entity.type_) &&
                Objects.equals(this.treePath, entity.treePath) &&
                Objects.equals(this.parentorganizationId, entity.parentorganizationId) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.direction, entity.direction) &&
                Objects.equals(this.personnels, entity.personnels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, organizationId, level, type_, treePath, parentorganizationId, name, direction, personnels);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "organizationId = " + organizationId + ", " +
                "level = " + level + ", " +
                "type_ = " + type_ + ", " +
                "treePath = " + treePath + ", " +
                "parentorganizationId = " + parentorganizationId + ", " +
                "name = " + name + ", " +
                "direction = " + direction + ", " +
                "personnels = " + personnels + ")";
    }
}