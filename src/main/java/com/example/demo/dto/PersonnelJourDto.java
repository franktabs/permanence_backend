package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.example.demo.entities.PersonnelJour}
 */
public class PersonnelJourDto implements Serializable {
    private Long id;
    @NotNull
    private PersonnelDto personnel;
    @NotNull
    private PermanenceDto permanence;
    private boolean responsable = false;

    private boolean isSubstitute = false;




    public PersonnelJourDto() {}
    public PersonnelJourDto(Long id) {
        this.id = id;
    }

    public PersonnelJourDto(Long id,  boolean responsable, boolean isSubstitute) {
        this.id = id;
        this.responsable = responsable;
        this.isSubstitute = isSubstitute;
    }


    public Long getId() {
        return id;
    }

    public PersonnelJourDto setId(Long id) {
        this.id = id;
        return this;
    }

    public PersonnelDto getPersonnel() {
        return personnel;
    }

    public PersonnelJourDto setPersonnel(PersonnelDto personnel) {
        this.personnel = personnel;
        return this;
    }

    public PermanenceDto getPermanence() {
        return permanence;
    }

    public PersonnelJourDto setPermanence(PermanenceDto permanence) {
        this.permanence = permanence;
        return this;
    }

    public boolean getResponsable() {
        return responsable;
    }

    public PersonnelJourDto setResponsable(boolean responsable) {
        this.responsable = responsable;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonnelJourDto entity = (PersonnelJourDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.personnel, entity.personnel) &&
                Objects.equals(this.permanence, entity.permanence) &&
                Objects.equals(this.responsable, entity.responsable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personnel, permanence, responsable);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "personnel = " + personnel + ", " +
                "permanence = " + permanence + ", " +
                "responsable = " + responsable + ")";
    }

    public boolean getIsSubstitute() {
        return isSubstitute;
    }

    public PersonnelJourDto setIsSubstitute(boolean isSubstitute) {
        this.isSubstitute = isSubstitute;
        return this;
    }
}