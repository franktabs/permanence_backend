package com.example.demo.entities;

/*import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/

import com.example.demo.entities.interfaces.Model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "perm_absence", indexes = {
        @Index(name = "personnel_id_UNIQUE_absence", columnList = "personnel_id, id", unique = true),
        @Index(name = "fk_perm_absence_personnel1_idx", columnList = "personnel_id")
})
public class Absence implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT not null")
    private Long id;

    @Size(max = 255)
    @Column(name = "message")
    private String message;

    @Size(max = 45)
    @Column(name = "motif", length = 45)
    private String motif;

    @Column(name = "validate")
    private Boolean validate;

    @Column(name = "submission_date")
    private LocalDate submissionDate;

    @Column(name = "start")
    private LocalDate start;

    @Column(name = "fin")
    private LocalDate fin;

    @Size(max = 45)
    @Column(name = "type", length = 45)
    private String type;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "personnel_id", nullable = false)
    private Personnel personnel;

    public Absence(){}
    public Absence(Long id, String message, String motif, Boolean validate, LocalDate submissionDate, LocalDate start, LocalDate fin, String type) {
        this.id = id;
        this.message = message;
        this.motif = motif;
        this.validate = validate;
        this.submissionDate = submissionDate;
        this.start = start;
        this.fin = fin;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Boolean getValidate() {
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

}