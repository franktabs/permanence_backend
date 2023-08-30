package com.example.demo.entities;

import com.example.demo.entities.interfaces.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "personnel_permanence")
public class PersonnelPermanence implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED not null")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permanence_id", nullable = false)
    private Permanence permanence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnel_jour")
    private Personnel personnelJour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personnel_nuit")
    private Personnel personnelNuit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Permanence getPermanence() {
        return permanence;
    }

    public void setPermanence(Permanence permanence) {
        this.permanence = permanence;
    }

    public Personnel getPersonnelJour() {
        return personnelJour;
    }

    public void setPersonnelJour(Personnel personnelJour) {
        this.personnelJour = personnelJour;
    }

    public Personnel getPersonnelNuit() {
        return personnelNuit;
    }

    public void setPersonnelNuit(Personnel personnelNuit) {
        this.personnelNuit = personnelNuit;
    }

}