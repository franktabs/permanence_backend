package com.afriland.dsi.permanence.dto;

import com.afriland.dsi.permanence.dto.interfaces.ModelDto;
import com.afriland.dsi.permanence.entities.Critere;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class GroupeDto implements Serializable, ModelDto {

    private Long id;

    @NotNull
    private String nom;

    Set<CritereDto> criteres = new LinkedHashSet<>();

    Set<PersonnelDto> personnels = new LinkedHashSet<>();

    public GroupeDto() {
    }

    public GroupeDto(Long id, String nom) {
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

    public Set<CritereDto> getCriteres() {
        return criteres;
    }

    public void setCriteres(Set<CritereDto> criteres) {
        this.criteres = criteres;
    }

    public Set<PersonnelDto> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(Set<PersonnelDto> personnels) {
        this.personnels = personnels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupeDto groupeDto = (GroupeDto) o;
        return Objects.equals(id, groupeDto.id) && Objects.equals(nom, groupeDto.nom) && Objects.equals(criteres, groupeDto.criteres) && Objects.equals(personnels, groupeDto.personnels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, criteres, personnels);
    }
}
