package com.afriland.dsi.permanence.entities;

/*import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/

import com.afriland.dsi.permanence.entities.interfaces.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "perm_annonce", indexes = {
        @Index(name = "fk_notification_personnel1_idx", columnList = "emetteur")
})
public class Annonce implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT not null")
    private Long id;

    @Size(max = 45)
    @Column(name = "type", length = 45)
    private String type;

    @Size(max = 255)
    @Column(name = "message")
    private String message;

    @Column(name = "submission_date")
    private Instant submissionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emetteur")
    private Personnel emetteur;


    @OneToMany(mappedBy = "annonce", cascade = CascadeType.PERSIST)
    private Set<Notification> notifications = new LinkedHashSet<>();

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Annonce(){}
    public Annonce(Long id, String type, String message, Instant submissionDate) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.submissionDate = submissionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Instant submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Personnel getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(Personnel emetteur) {
        this.emetteur = emetteur;
    }

}