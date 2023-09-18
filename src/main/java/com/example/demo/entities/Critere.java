package com.example.demo.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "critere", indexes = {@Index(name = "name_Unique_critere", columnList = "nom", unique = true)})
public class Critere {
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
}
