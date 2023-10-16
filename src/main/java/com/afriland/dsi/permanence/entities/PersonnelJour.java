package com.afriland.dsi.permanence.entities;

/*import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;*/

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.afriland.dsi.permanence.entities.interfaces.Model;
import com.afriland.dsi.permanence.entities.interfaces.PersonnelDay;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "perm_personnel_jour", indexes = {
        @Index(name = "fk_personnel_jour_permanence1_idx", columnList = "permanence_id"),
        @Index(name = "personnel_id_UNIQUE_personnel_jour", columnList = "personnel_id, permanence_id", unique = true),
        @Index(name = "fk_personnel_jour_personnel1_idx", columnList = "personnel_id")
})
public class PersonnelJour implements Model, PersonnelDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT not null")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "personnel_id", nullable = false)
    private Personnel personnel;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "permanence_id", nullable = false)
    private Permanence permanence;

    @NotNull
    @Column(name = "responsable", nullable = false)
    private Boolean responsable = false;

    @NotNull
    @Column(name = "is_substitute", nullable = false)
    private Boolean isSubstitute = false;

    public boolean getIsSubstitute() {
        return isSubstitute;
    }

    public void setIsSubstitute(boolean isSubstitute) {
        this.isSubstitute = isSubstitute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Permanence getPermanence() {
        return permanence;
    }

    public void setPermanence(Permanence permanence) {
        this.permanence = permanence;
    }

    public boolean getResponsable() {
        return responsable;
    }

    public void setResponsable(boolean responsable) {
        this.responsable = responsable;
    }

    public PersonnelJour(){}

    public PersonnelJour(Long id, @NotNull boolean responsable, @NotNull boolean isSubstitute) {
        this.id = id;
        this.responsable = responsable;
        this.isSubstitute=isSubstitute;
    }

    public PersonnelJour(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonnelJour{" +
                "id=" + id +
                ", personnel=" + personnel +

                ", responsable=" + responsable +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PersonnelJour that = (PersonnelJour) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}