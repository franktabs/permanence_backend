package com.example.demo.entities;

/*import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.entities.interfaces.Model;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "perm_permanence", indexes = {
        @Index(name = "fk_permanence_month1_idx", columnList = "month_id")
})
public class Permanence implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Size(max = 45)
    @NotNull
    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "month_id", nullable = false)
    private Month month;

    public Permanence() {
    }
    public Permanence(Long id, @NotNull LocalDate date, @NotNull String type) {
        this.id = id;
        this.date = date;
        this.type = type;
    }

    @OneToMany(mappedBy = "permanence", cascade = CascadeType.PERSIST)
    private Set<PersonnelJour> personnelJours = new LinkedHashSet<>();

    @OneToMany(mappedBy = "permanence", cascade = CascadeType.PERSIST)
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

    @Override
    public String toString() {
        return "Permanence{" +
                "id=" + id +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", month=" + month +
                ", personnelJours=" + personnelJours +
                ", personnelNuits=" + personnelNuits +
                '}';
    }
}