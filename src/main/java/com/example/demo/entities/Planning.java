package com.example.demo.entities;

/*import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;*/
import com.example.demo.entities.interfaces.Model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "perm_planning", indexes = {
        @Index(name = "fk_direction_planning_idx", columnList = "direction_id")
})
public class Planning implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "start", nullable = false)
    private LocalDate start;

    @NotNull
    @Column(name = "fin", nullable = false)
    private LocalDate fin;

    @NotNull
    @Column(name = "periode", nullable = false)
    private Integer periode;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "submission_date")
    private Instant submissionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "direction_id", columnDefinition = "INT")
    private Direction direction;
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Instant getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Instant submissionDate) {
        this.submissionDate = submissionDate;
    }

    @OneToMany(mappedBy = "planning", cascade = {CascadeType.PERSIST}
    )
    private Set<Month> months = new LinkedHashSet<>();
    public Set<Month> getMonths() {
        return months;
    }

    public void setMonths(Set<Month> months) {
        this.months = months;
    }

    public Planning() {
    }
    public Planning(Long id, @NotNull LocalDate start, @NotNull LocalDate fin, @NotNull Integer periode, Boolean isValid, @NotNull Instant submissionDate) {
        this.id = id;
        this.start = start;
        this.fin = fin;
        this.periode = periode;
        this.isValid = isValid;
        this.submissionDate = submissionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getPeriode() {
        return periode;
    }

    public void setPeriode(Integer periode) {
        this.periode = periode;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "Planning{" +
                "id=" + id +
                ", start=" + start +
                ", fin=" + fin +
                ", periode=" + periode +
                ", isValid=" + isValid +
                ", submissionDate=" + submissionDate +
                ", direction=" + direction +
                ", months=" + months +
                '}';
    }
}