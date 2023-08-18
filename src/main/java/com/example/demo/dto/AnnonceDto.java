package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.example.demo.entities.Annonce}
 */
public class AnnonceDto implements Serializable {
    private Long id;
    @NotNull
    private NotificationDto notification;
    @NotNull
    private PersonnelDto recepteur;
    private Boolean isViewed;
    private Boolean isDeleted;

    public AnnonceDto() {
    }

    public AnnonceDto(Long id, Boolean isViewed, Boolean isDeleted) {
        this.id = id;
        this.isViewed = isViewed;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public AnnonceDto setId(Long id) {
        this.id = id;
        return this;
    }

    public NotificationDto getNotification() {
        return notification;
    }

    public AnnonceDto setNotification(NotificationDto notification) {
        this.notification = notification;
        return this;
    }

    public PersonnelDto getRecepteur() {
        return recepteur;
    }

    public AnnonceDto setRecepteur(PersonnelDto recepteur) {
        this.recepteur = recepteur;
        return this;
    }

    public Boolean getIsViewed() {
        return isViewed;
    }

    public AnnonceDto setIsViewed(Boolean isViewed) {
        this.isViewed = isViewed;
        return this;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public AnnonceDto setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnonceDto entity = (AnnonceDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.notification, entity.notification) &&
                Objects.equals(this.recepteur, entity.recepteur) &&
                Objects.equals(this.isViewed, entity.isViewed) &&
                Objects.equals(this.isDeleted, entity.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notification, recepteur, isViewed, isDeleted);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "notification = " + notification + ", " +
                "recepteur = " + recepteur + ", " +
                "isViewed = " + isViewed + ", " +
                "isDeleted = " + isDeleted + ")";
    }
}