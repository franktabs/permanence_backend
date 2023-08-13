package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "permanence")
public class Permanence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED not null")
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Size(max = 45)
    @NotNull
    @Column(name = "type", nullable = false, length = 45)
    private String type;

    public Permanence() {
    }
    public Permanence(Long id, @NotNull LocalDate date, @NotNull String type) {
        this.id = id;
        this.date = date;
        this.type = type;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "month_id", nullable = false)
    private Month month;

    @OneToMany(mappedBy = "permanence", cascade = CascadeType.ALL)
    private Set<PersonnelJour> personnelJours = new LinkedHashSet<>();

    @OneToMany(mappedBy = "permanence", cascade = CascadeType.ALL)
    private Set<PersonnelNuit> personnelNuits = new LinkedHashSet<>();

    public Set<PersonnelNuit> getPersonnelNuits() {
        return personnelNuits;
    }

    public void setPersonnelNuits(Set<PersonnelNuit> personnelNuits) {
        this.personnelNuits = personnelNuits;
    }

    public Set<PersonnelJour> getPersonnelJours() {
        return personnelJours;
    }

    public void setPersonnelJours(Set<PersonnelJour> personnelJours) {
        this.personnelJours = personnelJours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Permanence that = (Permanence) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}