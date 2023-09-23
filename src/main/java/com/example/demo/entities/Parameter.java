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
@Entity
@Table(name = "perm_parameter", indexes = {
        @Index(name = "fk_parameter_direction1_idx", columnList = "direction_id")
})
public class Parameter implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 45)
    @NotNull
    @Column(name = "code", nullable = false, length = 45, unique = true)
    private String code;

    @Size(max = 45)
    @NotNull
    @Column(name = "libelle", nullable = false, length = 45, unique = true)
    private String libelle;

    @Size(max = 255)
    @Column(name = "valeur")
    private String valeur;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "direction_id", columnDefinition = "INT", nullable = true)
    private Direction direction;
    public Parameter(){}
    public Parameter(Long id, @NotNull String code, @NotNull String libelle, String valeur) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.valeur = valeur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}