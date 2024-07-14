package com.example.demo.entities;

import com.example.demo.entities.interfaces.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "perm_groupe", indexes = {@Index(name = "name_Unique_groupe", columnList = "nom", unique = true)})
public class Groupe implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 45)
    @NotNull
    @NotBlank
    @Column(name = "nom")
    private String nom;


    @OneToMany(mappedBy = "groupe", cascade = CascadeType.PERSIST)
    private Set<Personnel> personnels = new LinkedHashSet<>();


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "perm_groupe_critere",
    joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "critere_id", referencedColumnName = "id")
    )
    private Set<Critere> criteres = new LinkedHashSet<>();
    public Groupe() {
    }

    public Groupe(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

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

    public Set<Critere> getCriteres() {
        return criteres;
    }

    public void setCriteres(Set<Critere> criteres) {
        this.criteres = criteres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groupe groupe = (Groupe) o;
        return Objects.equals(id, groupe.id) && Objects.equals(nom, groupe.nom) && Objects.equals(personnels, groupe.personnels) && Objects.equals(criteres, groupe.criteres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, personnels, criteres);
    }
}