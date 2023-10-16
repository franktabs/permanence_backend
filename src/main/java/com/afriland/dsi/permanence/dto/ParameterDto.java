package com.afriland.dsi.permanence.dto;

import com.afriland.dsi.permanence.dto.interfaces.ModelDto;
import com.afriland.dsi.permanence.entities.Parameter;
/*import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Parameter}
 */
public class ParameterDto implements Serializable, ModelDto {
    private Long id;
    @NotNull
    @Size(max = 45)
    private String code;
    @NotNull
    @Size(max = 45)
    private String libelle;
    @Size(max = 255)
    private String valeur;

    private DirectionDto direction;

    public ParameterDto() {
    }

    public ParameterDto(Long id, @NotNull String code, @NotNull String libelle, String valeur) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.valeur = valeur;
    }


    public Long getId() {
        return id;
    }

    public ParameterDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ParameterDto setCode(String code) {
        this.code = code;
        return this;
    }

    public String getLibelle() {
        return libelle;
    }

    public ParameterDto setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public String getValeur() {
        return valeur;
    }

    public ParameterDto setValeur(String valeur) {
        this.valeur = valeur;
        return this;
    }

    public DirectionDto getDirection() {
        return direction;
    }

    public ParameterDto setDirection(DirectionDto direction) {
        this.direction = direction;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParameterDto entity = (ParameterDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.code, entity.code) &&
                Objects.equals(this.libelle, entity.libelle) &&
                Objects.equals(this.valeur, entity.valeur) &&
                Objects.equals(this.direction, entity.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, libelle, valeur, direction);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "code = " + code + ", " +
                "libelle = " + libelle + ", " +
                "valeur = " + valeur + ", " +
                "direction = " + direction + ")";
    }
}