package com.example.demo.entities;

/*import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/

import com.example.demo.entities.interfaces.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role", indexes = {
        @Index(name = "name_UNIQUE_role", columnList = "name", unique = true)
})
public class Role implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT not null")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "role_personnel",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "personnels_id", referencedColumnName = "id"))
    private Set<Personnel> personnels = new LinkedHashSet<>();

    public Set<Personnel> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(Set<Personnel> personnels) {
        this.personnels = personnels;
    }

    public Role() {
    }

    public Role(Long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(personnels, role.personnels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}