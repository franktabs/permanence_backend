package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "departement")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED not null")
    private Long id;

    @Column(name = "organizationId", columnDefinition = "INT UNSIGNED not null")
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
    private Integer parentorganizationId;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade =  {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "direction_id", nullable = false)
    private Direction direction;

    @OneToMany(mappedBy = "departement", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private Set<Personnel> personnels = new LinkedHashSet<>();

    public Set<Personnel> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(Set<Personnel> personnels) {
        this.personnels = personnels;
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

    public Integer getParentorganizationId() {
        return parentorganizationId;
    }

    public void setParentorganizationId(Integer parentorganizationId) {
        this.parentorganizationId = parentorganizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Departement that = (Departement) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}