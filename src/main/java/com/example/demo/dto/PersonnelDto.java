package com.example.demo.dto;

import com.example.demo.entities.Absence;
import com.example.demo.entities.Remplacement;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.example.demo.entities.Personnel}
 */
public class PersonnelDto implements Serializable {
    private Long id;
    @NotNull
    @Size(max = 255)
    private String firstname;
    @NotNull
    @Size(max = 255)
    private String emailaddress;
    @Size(max = 45)
    private String telephoneCisco;
    @Size(max = 45)
    private String telephoneMobile;
    @NotNull
    private Integer userId;
    @NotNull
    private Character sexe;
    @Size(max = 255)
    private String fonction;
    @Size(max = 45)
    private String service;
    @Size(max = 255)
    private String libAge;
    private Integer organizationId;
    private Boolean agent;
    @NotNull
    private DepartementDto departement;
    private Set<Remplacement> remplacements;
    private Set<Absence> absences;

    public PersonnelDto(Long id, @NotNull String firstname, @NotNull String emailaddress, String telephoneCisco, String telephoneMobile, @NotNull Integer userId, @NotNull Character sexe, String fonction, String service, String libAge, Integer organizationId, Boolean agent) {
        this.id = id;
        this.firstname = firstname;
        this.emailaddress = emailaddress;
        this.telephoneCisco = telephoneCisco;
        this.telephoneMobile = telephoneMobile;
        this.userId = userId;
        this.sexe = sexe;
        this.fonction = fonction;
        this.service = service;
        this.libAge = libAge;
        this.organizationId = organizationId;
        this.agent = agent;
    }

    public void setDepartement(DepartementDto departement) {
        this.departement = departement;
    }

    public void setRemplacements(Set<Remplacement> remplacements) {
        this.remplacements = remplacements;
    }

    public void setAbsences(Set<Absence> absences) {
        this.absences = absences;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public String getTelephoneCisco() {
        return telephoneCisco;
    }

    public String getTelephoneMobile() {
        return telephoneMobile;
    }

    public Integer getUserId() {
        return userId;
    }

    public Character getSexe() {
        return sexe;
    }

    public String getFonction() {
        return fonction;
    }

    public String getService() {
        return service;
    }

    public String getLibAge() {
        return libAge;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public Boolean getAgent() {
        return agent;
    }

    public DepartementDto getDepartement() {
        return departement;
    }

    public Set<Remplacement> getRemplacements() {
        return remplacements;
    }

    public Set<Absence> getAbsences() {
        return absences;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonnelDto entity = (PersonnelDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.firstname, entity.firstname) &&
                Objects.equals(this.emailaddress, entity.emailaddress) &&
                Objects.equals(this.telephoneCisco, entity.telephoneCisco) &&
                Objects.equals(this.telephoneMobile, entity.telephoneMobile) &&
                Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.sexe, entity.sexe) &&
                Objects.equals(this.fonction, entity.fonction) &&
                Objects.equals(this.service, entity.service) &&
                Objects.equals(this.libAge, entity.libAge) &&
                Objects.equals(this.organizationId, entity.organizationId) &&
                Objects.equals(this.agent, entity.agent) &&
                Objects.equals(this.departement, entity.departement) &&
                Objects.equals(this.remplacements, entity.remplacements) &&
                Objects.equals(this.absences, entity.absences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, emailaddress, telephoneCisco, telephoneMobile, userId, sexe, fonction, service, libAge, organizationId, agent, departement, remplacements, absences);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstname = " + firstname + ", " +
                "emailaddress = " + emailaddress + ", " +
                "telephoneCisco = " + telephoneCisco + ", " +
                "telephoneMobile = " + telephoneMobile + ", " +
                "userId = " + userId + ", " +
                "sexe = " + sexe + ", " +
                "fonction = " + fonction + ", " +
                "service = " + service + ", " +
                "libAge = " + libAge + ", " +
                "organizationId = " + organizationId + ", " +
                "agent = " + agent + ", " +
                "departement = " + departement + ", " +
                "remplacements = " + remplacements + ", " +
                "absences = " + absences + ")";
    }
}