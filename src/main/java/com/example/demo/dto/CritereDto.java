package com.example.demo.dto;

import com.example.demo.dto.interfaces.ModelDto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class CritereDto implements Serializable, ModelDto {

    private Long id;

    @NotNull
    private String nom;

    private Set<GroupeDto> groupeDtos = new LinkedHashSet<>();

    public CritereDto() {
    }

    public CritereDto(Long id, String nom) {
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

    public Set<GroupeDto> getGroupeDtos() {
        return groupeDtos;
    }

    public void setGroupeDtos(Set<GroupeDto> groupeDtos) {
        this.groupeDtos = groupeDtos;
    }
}
