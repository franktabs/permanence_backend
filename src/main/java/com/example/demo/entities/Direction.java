package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "direction")
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED not null")
    private Long id;

    @Column(name = "organizationId", unique = true, columnDefinition = "INT UNSIGNED not null")
    private Long organizationId;

    @Column(name = "level", columnDefinition = "INT UNSIGNED")
    private Long level;

    @Size(max = 45)
    @Column(name = "type_", length = 45)
    private String type;

    @Size(max = 255)
    @Column(name = "treepath")
    private String treepath;

    @Column(name = "parentorganizationId")
    private Long parentorganizationId;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    public Direction() {
    }
    public Direction(Long id, Long organizationId, Long level, String type, String treepath, Long parentorganizationId, @NotNull String name) {
        this.id = id;
        this.organizationId = organizationId;
        this.level = level;
        this.type = type;
        this.treepath = treepath;
        this.parentorganizationId = parentorganizationId;
        this.name = name;
    }

    @OneToMany(mappedBy = "direction", cascade = {CascadeType.PERSIST})
    private Set<Departement> departements = new LinkedHashSet<>();

    @OneToMany(mappedBy = "direction", cascade = CascadeType.PERSIST)
    private Set<Parameter> parameters = new LinkedHashSet<>();

    public Set<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(Set<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Set<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(Set<Departement> departements) {
        this.departements = departements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTreepath() {
        return treepath;
    }

    public void setTreepath(String treepath) {
        this.treepath = treepath;
    }

    public Long getParentorganizationId() {
        return parentorganizationId;
    }

    public void setParentorganizationId(Long parentorganizationId) {
        this.parentorganizationId = parentorganizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "id=" + id +
                ", organizationId=" + organizationId +
                ", level=" + level +
                ", type='" + type + '\'' +
                ", treepath='" + treepath + '\'' +
                ", parentorganizationId=" + parentorganizationId +
                ", name='" + name + '\'' +
                ", departements=" + departements +
                '}';
    }
}