package com.example.demo.dto;

import com.example.demo.entities.Absence;
import com.example.demo.entities.Personnel;
import com.example.demo.entities.Remplacement;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Personnel}
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
    private Set<RemplacementDto> absentList;
    private Set<AbsenceDto> vacancies;

    private Set<PersonnelJourDto> personnels_jour;

    private Set<PersonnelNuitDto> personnels_nuit;
    private Set<MonthDto> months_supervise;

    private Set<RoleDto> roles;

    private Set<RemplacementDto> remplacements = new LinkedHashSet<>();
    private Set<AbsenceDto> absences = new LinkedHashSet<>();
    private Set<PersonnelJourDto> personnelJours = new LinkedHashSet<>();
    private Set<PersonnelNuitDto> personnelNuits = new LinkedHashSet<>();
    private Set<NotificationDto> notifications = new LinkedHashSet<>();

    private Set<AnnonceDto> annonces;

    public Set<AnnonceDto> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(Set<AnnonceDto> annonces) {
        this.annonces = annonces;
    }

    public PersonnelDto() {
    }
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


    public Set<PersonnelJourDto> getPersonnels_jour() {
        return personnels_jour;
    }

    public void setPersonnels_jour(Set<PersonnelJourDto> personnels_jour) {
        this.personnels_jour = personnels_jour;
    }

    public Set<PersonnelNuitDto> getPersonnels_nuit() {
        return personnels_nuit;
    }

    public void setPersonnels_nuit(Set<PersonnelNuitDto> personnels_nuit) {
        this.personnels_nuit = personnels_nuit;
    }

    public void setDepartement(DepartementDto departement) {
        this.departement = departement;
    }

    public void setRemplacements(Set<RemplacementDto> absentList) {
        this.absentList = absentList;
    }

    public void setAbsences(Set<AbsenceDto> vacancies) {
        this.vacancies = vacancies;
    }

    public void setRoles(Set<RoleDto> roles){this.roles = roles;}

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

    public Set<RemplacementDto> getAbsentList() {
        return absentList;
    }

    public Set<AbsenceDto> getVacancies() {
        return vacancies;
    }

    public Set<RoleDto> getRoles(){
        return roles;
    }


    public void setNotifications(Set<NotificationDto> notifications) {
        this.notifications = notifications;
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
                Objects.equals(this.absentList, entity.absentList) &&
                Objects.equals(this.vacancies, entity.vacancies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, emailaddress, telephoneCisco, telephoneMobile, userId, sexe, fonction, service, libAge, organizationId, agent, departement, absentList, vacancies);
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
                "absentList = " + absentList + ", " +
                "vacancies = " + vacancies + ")";
    }

    public void setMonths_supervise(Set<MonthDto> months_supervise) {
        this.months_supervise = months_supervise;
    }

    public Set<MonthDto> getMonths_supervise() {
        return months_supervise;
    }

    public Set<RemplacementDto> getRemplacements() {
        return remplacements;
    }

    public Set<AbsenceDto> getAbsences() {
        return absences;
    }

    public Set<PersonnelJourDto> getPersonnelJours() {
        return personnelJours;
    }

    public Set<PersonnelNuitDto> getPersonnelNuits() {
        return personnelNuits;
    }

    public Set<NotificationDto> getNotifications() {
        return notifications;
    }
}