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
@Table(name = "month", indexes = {
        @Index(name = "fk_month_personnel1_idx", columnList = "superviseur"),
        @Index(name = "fk_month_planning1_idx", columnList = "planning_id")
})
public class Month implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT not null")
    private Long id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "numero", columnDefinition = "INT not null")
    private Long numero;

    @Column(name = "start")
    private LocalDate start;

    @Column(name = "fin")
    private LocalDate fin;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "superviseur", nullable = false)
    private Personnel superviseur;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "planning_id", nullable = false)
    private Planning planning;
    public Month(){}

    public Month(Long id, String name, Long numero, LocalDate start, LocalDate fin) {
        this.id = id;
        this.name = name;
        this.numero = numero;
        this.start = start;
        this.fin = fin;
    }



    @OneToMany(mappedBy = "month", cascade = CascadeType.PERSIST)
    private Set<Permanence> permanences = new LinkedHashSet<>();

    public Set<Permanence> getPermanences() {
        return permanences;
    }

    public void setPermanences(Set<Permanence> permanences) {
        this.permanences = permanences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
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

    public Personnel getSuperviseur() {
        return superviseur;
    }

    public void setSuperviseur(Personnel superviseur) {
        this.superviseur = superviseur;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Month month = (Month) o;
        return getId() != null && Objects.equals(getId(), month.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Month{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numero=" + numero +
                ", start=" + start +
                ", fin=" + fin +
                ", superviseur=" + superviseur +

                '}';
    }
}