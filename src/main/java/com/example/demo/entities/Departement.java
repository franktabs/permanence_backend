package com.example.demo.entities;

/*import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.entities.interfaces.Model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "perm_departement", indexes = {
        @Index(name = "fk_departement_direction1_idx", columnList = "direction_id"),
        @Index(name = "organizationId_UNIQUE_departement", columnList = "organization_id", unique = true),
        @Index(name = "direction_id_UNIQUE_departement", columnList = "direction_id, id", unique = true)
})
public class Departement implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT not null")
    private Long id;

    @Column(name = "organization_id", columnDefinition = "INT not null")
    private Long organizationId;

    @Column(name = "level", columnDefinition = "INT")
    private Long level;

    @Size(max = 45)
    @Column(name = "type_", length = 45)
    private String type;

    @Size(max = 255)
    @Column(name = "treepath")
    private String treepath;

    @Column(name = "parentorganization_id")
    private Long parentorganizationId;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "direction_id", nullable = false)
    private Direction direction;

    @OneToMany(mappedBy = "departement", cascade = {CascadeType.PERSIST})
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

    public Long getParentorganizationId() {
        return parentorganizationId;
    }

    public void setParentorganizationId(Long parentorganizationId) {
        this.parentorganizationId = parentorganizationId;
    }

    public Departement() {
    }
    public Departement(Long id, Long organizationId, Long level, String type, String treepath, Long parentorganizationId, @NotNull String name) {
        this.id = id;
        this.organizationId = organizationId;
        this.level = level;
        this.type = type;
        this.treepath = treepath;
        this.parentorganizationId = parentorganizationId;
        this.name = name;
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
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", organizationId=" + organizationId +
                ", level=" + level +
                ", type='" + type + '\'' +
                ", treepath='" + treepath + '\'' +
                ", name='" + name + '\'' +
                '}';
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