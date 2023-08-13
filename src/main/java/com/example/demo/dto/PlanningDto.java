package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.example.demo.entities.Planning}
 */
public class PlanningDto implements Serializable {
    private Long id;
    @NotNull
    private LocalDate start;
    @NotNull
    private LocalDate end;
    @NotNull
    private Integer periode;
    private Boolean isValid;
    private Set<MonthDto> months = new LinkedHashSet<>();

    public PlanningDto() {
    }

    public PlanningDto(Long id, @NotNull LocalDate start, @NotNull LocalDate end, @NotNull Integer periode, Boolean isValid) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.periode = periode;
        this.isValid = isValid;
    }

    public Long getId() {
        return id;
    }

    public PlanningDto setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getStart() {
        return start;
    }

    public PlanningDto setStart(LocalDate start) {
        this.start = start;
        return this;
    }

    public LocalDate getEnd() {
        return end;
    }

    public PlanningDto setEnd(LocalDate end) {
        this.end = end;
        return this;
    }

    public Integer getPeriode() {
        return periode;
    }

    public PlanningDto setPeriode(Integer periode) {
        this.periode = periode;
        return this;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public PlanningDto setIsValid(Boolean isValid) {
        this.isValid = isValid;
        return this;
    }

    public Set<MonthDto> getMonths() {
        return months;
    }

    public PlanningDto setMonths(Set<MonthDto> months) {
        this.months = months;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanningDto entity = (PlanningDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.start, entity.start) &&
                Objects.equals(this.end, entity.end) &&
                Objects.equals(this.periode, entity.periode) &&
                Objects.equals(this.isValid, entity.isValid) &&
                Objects.equals(this.months, entity.months);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, start, end, periode, isValid, months);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "start = " + start + ", " +
                "end = " + end + ", " +
                "periode = " + periode + ", " +
                "isValid = " + isValid + ", " +
                "months = " + months + ")";
    }
}