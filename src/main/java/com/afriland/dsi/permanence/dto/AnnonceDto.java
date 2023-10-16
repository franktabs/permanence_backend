package com.afriland.dsi.permanence.dto;

import com.afriland.dsi.permanence.dto.interfaces.ModelDto;
import com.afriland.dsi.permanence.entities.Annonce;
/*import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Annonce}
 */
public class AnnonceDto implements Serializable, ModelDto {
    private Long id;
    @Size(max = 45)
    private String type;
    @Size(max = 255)
    private String message;
    private Instant submissionDate;
    @NotNull
    private PersonnelDto emetteur;
    private Set<NotificationDto> annonces = new LinkedHashSet<>();

    public AnnonceDto() {
    }

    public AnnonceDto(Long id, String type, String message, Instant submissionDate) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.submissionDate = submissionDate;
    }

    public Long getId() {
        return id;
    }

    public AnnonceDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public AnnonceDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AnnonceDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public Instant getSubmissionDate() {
        return submissionDate;
    }

    public AnnonceDto setSubmissionDate(Instant submissionDate) {
        this.submissionDate = submissionDate;
        return this;
    }

    public PersonnelDto getEmetteur() {
        return emetteur;
    }

    public AnnonceDto setEmetteur(PersonnelDto emetteur) {
        this.emetteur = emetteur;
        return this;
    }

    public Set<NotificationDto> getAnnonces() {
        return annonces;
    }

    public AnnonceDto setAnnonces(Set<NotificationDto> annonces) {
        this.annonces = annonces;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnonceDto entity = (AnnonceDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.message, entity.message) &&
                Objects.equals(this.submissionDate, entity.submissionDate) &&
                Objects.equals(this.emetteur, entity.emetteur) &&
                Objects.equals(this.annonces, entity.annonces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, message, submissionDate, emetteur, annonces);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "type = " + type + ", " +
                "message = " + message + ", " +
                "submissionDate = " + submissionDate + ", " +
                "emetteur = " + emetteur + ", " +
                "annonces = " + annonces + ")";
    }
}