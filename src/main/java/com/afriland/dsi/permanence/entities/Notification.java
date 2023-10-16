package com.afriland.dsi.permanence.entities;

/*import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;*/
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.afriland.dsi.permanence.entities.interfaces.Model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "perm_notification", indexes = {
        @Index(name = "notification_id_UNIQUE_notification", columnList = "annonce_id, recepteur", unique = true),
        @Index(name = "fk_notification_has_personnel_personnel1_idx", columnList = "recepteur")
})
public class Notification implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT not null")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "annonce_id", nullable = false)
    private Annonce annonce;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "recepteur", nullable = false)
    private Personnel recepteur;

    @Column(name = "is_viewed")
    private Boolean isViewed;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Notification(){}
    public Notification(Long id, Boolean isViewed, Boolean isDeleted) {
        this.id = id;
        this.isViewed = isViewed;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Personnel getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(Personnel recepteur) {
        this.recepteur = recepteur;
    }

    public Boolean getIsViewed() {
        return isViewed;
    }

    public void setIsViewed(Boolean isViewed) {
        this.isViewed = isViewed;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Notification notification = (Notification) o;
        return getId() != null && Objects.equals(getId(), notification.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}