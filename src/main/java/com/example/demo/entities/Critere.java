package com.example.demo.entities;


import com.example.demo.entities.interfaces.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "critere", indexes = {@Index(name = "name_Unique_critere", columnList = "nom", unique = true)})
public class Critere implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT not null")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "nom", unique = true)
    private String nom;

    @ManyToMany(mappedBy = "criteres", cascade = CascadeType.PERSIST)
    private Set<Groupe> groupes = new LinkedHashSet<>();


    public Critere() {
    }

    public Critere(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(Set<Groupe> groupes) {
        this.groupes = groupes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Critere critere = (Critere) o;
        return Objects.equals(id, critere.id) && Objects.equals(nom, critere.nom) && Objects.equals(groupes, critere.groupes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, groupes);
    }
}
