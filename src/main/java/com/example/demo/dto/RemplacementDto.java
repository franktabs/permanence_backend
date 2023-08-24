package com.example.demo.dto;

/*import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link com.example.demo.entities.Remplacement}
 */
public class RemplacementDto implements Serializable {
    private Long id;
    @Size(max = 255)
    private String message;
    @Size(max = 45)
    private String motif;
    private Boolean validate;
    private LocalDate submissionDate;
    private LocalDate start;
    private LocalDate end;
    @NotNull
    private PersonnelDto personnel;
    @NotNull
    private PersonnelDto remplaceur;

    public RemplacementDto() {
    }

    public RemplacementDto(Long id, String message, String motif, Boolean validate, LocalDate submissionDate, LocalDate start, LocalDate end) {
        this.id = id;
        this.message = message;
        this.motif = motif;
        this.validate = validate;
        this.submissionDate = submissionDate;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public RemplacementDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RemplacementDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMotif() {
        return motif;
    }

    public RemplacementDto setMotif(String motif) {
        this.motif = motif;
        return this;
    }

    public Boolean getValidate() {
        return validate;
    }

    public RemplacementDto setValidate(Boolean validate) {
        this.validate = validate;
        return this;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public RemplacementDto setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
        return this;
    }

    public LocalDate getStart() {
        return start;
    }

    public RemplacementDto setStart(LocalDate start) {
        this.start = start;
        return this;
    }

    public LocalDate getEnd() {
        return end;
    }

    public RemplacementDto setEnd(LocalDate end) {
        this.end = end;
        return this;
    }

    public PersonnelDto getPersonnel() {
        return personnel;
    }

    public RemplacementDto setPersonnel(PersonnelDto personnel) {
        this.personnel = personnel;
        return this;
    }

    public PersonnelDto getRemplaceur() {
        return remplaceur;
    }

    public RemplacementDto setRemplaceur(PersonnelDto remplaceur) {
        this.remplaceur = remplaceur;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemplacementDto entity = (RemplacementDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.message, entity.message) &&
                Objects.equals(this.motif, entity.motif) &&
                Objects.equals(this.validate, entity.validate) &&
                Objects.equals(this.submissionDate, entity.submissionDate) &&
                Objects.equals(this.start, entity.start) &&
                Objects.equals(this.end, entity.end) &&
                Objects.equals(this.personnel, entity.personnel) &&
                Objects.equals(this.remplaceur, entity.remplaceur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, motif, validate, submissionDate, start, end, personnel, remplaceur);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "message = " + message + ", " +
                "motif = " + motif + ", " +
                "validate = " + validate + ", " +
                "submissionDate = " + submissionDate + ", " +
                "start = " + start + ", " +
                "end = " + end + ", " +
                "personnel = " + personnel + ", " +
                "remplaceur = " + remplaceur + ")";
    }
}