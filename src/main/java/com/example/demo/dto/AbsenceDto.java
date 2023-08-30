package com.example.demo.dto;

/*import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/

import com.example.demo.dto.interfaces.ModelDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link com.example.demo.entities.Absence}
 */
public class AbsenceDto implements Serializable, ModelDto {
    private Long id;
    @Size(max = 255)
    private String message;
    @Size(max = 45)
    private String motif;
    private Boolean validate = null;
    private LocalDate submissionDate;
    private LocalDate start;
    private LocalDate end;
    @Size(max = 45)
    private String type_;
    @NotNull
    private PersonnelDto personnel;

    public AbsenceDto() {
    }

    public AbsenceDto(Long id, String message, String motif, Boolean validate, LocalDate submissionDate, LocalDate start, LocalDate end, String type_) {
        this.id = id;
        this.message = message;
        this.motif = motif;
        this.validate = validate;
        this.submissionDate = submissionDate;
        this.start = start;
        this.end = end;
        this.type_ = type_;
    }

    public Long getId() {
        return id;
    }

    public AbsenceDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AbsenceDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMotif() {
        return motif;
    }

    public AbsenceDto setMotif(String motif) {
        this.motif = motif;
        return this;
    }

    public Boolean getValidate() {
        return validate;
    }

    public AbsenceDto setValidate(Boolean validate) {
        this.validate = validate;
        return this;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public AbsenceDto setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
        return this;
    }

    public LocalDate getStart() {
        return start;
    }

    public AbsenceDto setStart(LocalDate start) {
        this.start = start;
        return this;
    }

    public LocalDate getEnd() {
        return end;
    }

    public AbsenceDto setEnd(LocalDate end) {
        this.end = end;
        return this;
    }

    public String getType_() {
        return type_;
    }

    public AbsenceDto setType_(String type_) {
        this.type_ = type_;
        return this;
    }

    public PersonnelDto getPersonnel() {
        return personnel;
    }

    public AbsenceDto setPersonnel(PersonnelDto personnel) {
        this.personnel = personnel;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbsenceDto entity = (AbsenceDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.message, entity.message) &&
                Objects.equals(this.motif, entity.motif) &&
                Objects.equals(this.validate, entity.validate) &&
                Objects.equals(this.submissionDate, entity.submissionDate) &&
                Objects.equals(this.start, entity.start) &&
                Objects.equals(this.end, entity.end) &&
                Objects.equals(this.type_, entity.type_) &&
                Objects.equals(this.personnel, entity.personnel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, motif, validate, submissionDate, start, end, type_, personnel);
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
                "type_ = " + type_ + ", " +
                "personnel = " + personnel + ")";
    }
}