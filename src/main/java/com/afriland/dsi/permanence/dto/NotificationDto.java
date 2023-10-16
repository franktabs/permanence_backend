package com.afriland.dsi.permanence.dto;

import com.afriland.dsi.permanence.dto.interfaces.ModelDto;
import com.afriland.dsi.permanence.entities.Notification;
/*import jakarta.validation.constraints.NotNull;*/

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Notification}
 */
public class NotificationDto implements Serializable, ModelDto {
    private Long id;
    @NotNull
    private AnnonceDto annonce;
    @NotNull
    private PersonnelDto recepteur;
    private Boolean isViewed;
    private Boolean isDeleted;

    public NotificationDto() {
    }

    public NotificationDto(Long id, Boolean isViewed, Boolean isDeleted) {
        this.id = id;
        this.isViewed = isViewed;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public NotificationDto setId(Long id) {
        this.id = id;
        return this;
    }

    public AnnonceDto getAnnonce() {
        return annonce;
    }

    public NotificationDto setAnnonce(AnnonceDto annonce) {
        this.annonce = annonce;
        return this;
    }

    public PersonnelDto getRecepteur() {
        return recepteur;
    }

    public NotificationDto setRecepteur(PersonnelDto recepteur) {
        this.recepteur = recepteur;
        return this;
    }

    public Boolean getIsViewed() {
        return isViewed;
    }

    public NotificationDto setIsViewed(Boolean isViewed) {
        this.isViewed = isViewed;
        return this;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public NotificationDto setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDto entity = (NotificationDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.annonce, entity.annonce) &&
                Objects.equals(this.recepteur, entity.recepteur) &&
                Objects.equals(this.isViewed, entity.isViewed) &&
                Objects.equals(this.isDeleted, entity.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, annonce, recepteur, isViewed, isDeleted);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "annonce = " + annonce + ", " +
                "recepteur = " + recepteur + ", " +
                "isViewed = " + isViewed + ", " +
                "isDeleted = " + isDeleted + ")";
    }
}