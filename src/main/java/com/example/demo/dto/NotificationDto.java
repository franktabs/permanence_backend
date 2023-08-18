package com.example.demo.dto;

import com.example.demo.entities.Annonce;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.example.demo.entities.Notification}
 */
public class NotificationDto implements Serializable {
    private Long id;
    @Size(max = 45)
    private String type;
    @Size(max = 255)
    private String message;
    private Instant submissionDate;
    @NotNull
    private PersonnelDto emetteur;
    private Set<AnnonceDto> annonces = new LinkedHashSet<>();

    public NotificationDto() {
    }

    public NotificationDto(Long id, String type, String message, Instant submissionDate) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.submissionDate = submissionDate;
    }

    public Long getId() {
        return id;
    }

    public NotificationDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public NotificationDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public NotificationDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public Instant getSubmissionDate() {
        return submissionDate;
    }

    public NotificationDto setSubmissionDate(Instant submissionDate) {
        this.submissionDate = submissionDate;
        return this;
    }

    public PersonnelDto getEmetteur() {
        return emetteur;
    }

    public NotificationDto setEmetteur(PersonnelDto emetteur) {
        this.emetteur = emetteur;
        return this;
    }

    public Set<AnnonceDto> getAnnonces() {
        return annonces;
    }

    public NotificationDto setAnnonces(Set<AnnonceDto> annonces) {
        this.annonces = annonces;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDto entity = (NotificationDto) o;
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