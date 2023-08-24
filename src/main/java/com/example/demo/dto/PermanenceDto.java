package com.example.demo.dto;

import com.example.demo.entities.Month;
/*import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.example.demo.entities.Permanence}
 */
public class PermanenceDto implements Serializable {
    private Long id;
    @NotNull
    private LocalDate date;
    @NotNull
    @Size(max = 45)
    private String type;
    @NotNull
    private MonthDto month;

    private Set<PersonnelJourDto> personnels_jour;

    private Set<PersonnelNuitDto> personnels_nuit;

    public PermanenceDto() {
    }

    public PermanenceDto(Long id, @NotNull LocalDate date, @NotNull String type ) {
        this.id = id;
        this.date = date;
        this.type = type;
    }

    public Set<PersonnelJourDto> getPersonnels_jour() {
        return personnels_jour;
    }

    public void setPersonnels_jour(Set<PersonnelJourDto> personnels_jour) {
        this.personnels_jour = personnels_jour;
    }

    public Set<PersonnelNuitDto> getPersonnels_nuit() {
        return personnels_nuit;
    }

    public void setPersonnels_nuit(Set<PersonnelNuitDto> personnels_nuit) {
        this.personnels_nuit = personnels_nuit;
    }

    public Long getId() {
        return id;
    }

    public PermanenceDto setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public PermanenceDto setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getType() {
        return type;
    }

    public PermanenceDto setType(String type) {
        this.type = type;
        return this;
    }

    public MonthDto getMonth() {
        return month;
    }

    public PermanenceDto setMonth(MonthDto month) {
        this.month = month;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermanenceDto entity = (PermanenceDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.month, entity.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, type, month);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "date = " + date + ", " +
                "type = " + type + ", " +
                "month = " + month + ")";
    }
}