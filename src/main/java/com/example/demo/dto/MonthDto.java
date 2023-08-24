package com.example.demo.dto;

import com.example.demo.entities.Planning;
/*import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.example.demo.entities.Month}
 */
public class MonthDto implements Serializable {
    private Long id;
    @Size(max = 45)
    private String name;
    private Long numero;
    private LocalDate start;
    private LocalDate end;
    @NotNull
    private PersonnelDto superviseur;
    @NotNull
    private PlanningDto planning;

    private Set<PermanenceDto> permanences = new LinkedHashSet<>();


    public MonthDto() {
    }

    public MonthDto(Long id, String name, Long numero, LocalDate start, LocalDate end) {
        this.id = id;
        this.name = name;
        this.numero = numero;
        this.start = start;
        this.end = end;
    }


    public Long getId() {
        return id;
    }

    public MonthDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MonthDto setName(String name) {
        this.name = name;
        return this;
    }

    public Long getNumero() {
        return numero;
    }

    public MonthDto setNumero(Long numero) {
        this.numero = numero;
        return this;
    }

    public LocalDate getStart() {
        return start;
    }

    public MonthDto setStart(LocalDate start) {
        this.start = start;
        return this;
    }

    public LocalDate getEnd() {
        return end;
    }

    public MonthDto setEnd(LocalDate end) {
        this.end = end;
        return this;
    }

    public PersonnelDto getSuperviseur() {
        return superviseur;
    }

    public MonthDto setSuperviseur(PersonnelDto superviseur) {
        this.superviseur = superviseur;
        return this;
    }

    public PlanningDto getPlanning() {
        return planning;
    }

    public MonthDto setPlanning(PlanningDto planning) {
        this.planning = planning;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthDto entity = (MonthDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.numero, entity.numero) &&
                Objects.equals(this.start, entity.start) &&
                Objects.equals(this.end, entity.end) &&
                Objects.equals(this.superviseur, entity.superviseur) &&
                Objects.equals(this.planning, entity.planning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numero, start, end, superviseur, planning);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "numero = " + numero + ", " +
                "start = " + start + ", " +
                "end = " + end + ", " +
                "superviseur = " + superviseur + ", " +
                "planning = " + planning + ")";
    }

    public Set<PermanenceDto> getPermanences() {
        return permanences;
    }

    public MonthDto setPermanences(Set<PermanenceDto> permanences) {
        this.permanences = permanences;
        return this;
    }
}