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

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "personnel", indexes = {
        @Index(name = "fk_personnel_departement_idx", columnList = "departement_id"),
        @Index(name = "departement_id_UNIQUE_personnel", columnList = "departement_id, id", unique = true),
        @Index(name = "userId_UNIQUE_personnel", columnList = "user_id", unique = true),
        @Index(name = "emailaddress_UNIQUE_personnel", columnList = "emailaddress", unique = true)
})
public class Personnel implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT not null")
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Size(max = 255)
    @NotNull
    @Column(name = "emailaddress", nullable = false)
    private String emailaddress;

    @Size(max = 45)
    @Column(name = "telephone_cisco", length = 45)
    private String telephoneCisco;

    @Size(max = 45)
    @Column(name = "telephone_mobile", length = 45)
    private String telephoneMobile;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotNull
    @Column(name = "sexe", nullable = false)
    private Character sexe;

    @Size(max = 255)
    @Column(name = "fonction")
    private String fonction;

    @Size(max = 45)
    @Column(name = "service", length = 45)
    private String service;

    @Size(max = 255)
    @Column(name = "lib_age")
    private String libAge;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "agent")
    private Boolean agent;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;

    @Size(max = 255)
    @Column(name = "screenname")
    private String screenname;

    @OneToMany(mappedBy = "personnel", cascade = {CascadeType.PERSIST})
    private Set<Remplacement> remplacements = new LinkedHashSet<>();

    @OneToMany(mappedBy = "personnel", cascade = {CascadeType.PERSIST})
    private Set<Absence> absences = new LinkedHashSet<>();

    @OneToMany(mappedBy = "personnel", cascade = CascadeType.PERSIST)
    private Set<PersonnelJour> personnelJours = new LinkedHashSet<>();


    @OneToMany(mappedBy = "personnel", cascade = CascadeType.PERSIST)
    private Set<PersonnelNuit> personnelNuits = new LinkedHashSet<>();

    @OneToMany(mappedBy = "superviseur", cascade = CascadeType.PERSIST)
    private Set<Month> months_supervise = new LinkedHashSet<>();


    @OneToMany(mappedBy = "recepteur", cascade = CascadeType.PERSIST)
    private Set<Notification> notifications = new LinkedHashSet<>();

    @OneToMany(mappedBy = "emetteur", cascade = CascadeType.PERSIST)
    private Set<Annonce> annonces = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "personnels", cascade = CascadeType.PERSIST)
    private Set<Role> roles = new LinkedHashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public Set<Annonce> getAnnonces() {
        return annonces;
    }

    public void setAnnonces(Set<Annonce> annonces) {
        this.annonces = annonces;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }


    public Set<Month> getMonths_supervise() {
        return months_supervise;
    }

    public void setMonths_supervise(Set<Month> months_supervise) {
        this.months_supervise = months_supervise;
    }

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

    public Set<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(Set<Absence> absences) {
        this.absences = absences;
    }

    public Set<Remplacement> getRemplacements() {
        return remplacements;
    }

    public void setRemplacements(Set<Remplacement> remplacements) {
        this.remplacements = remplacements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getTelephoneCisco() {
        return telephoneCisco;
    }

    public void setTelephoneCisco(String telephoneCisco) {
        this.telephoneCisco = telephoneCisco;
    }

    public String getTelephoneMobile() {
        return telephoneMobile;
    }

    public void setTelephoneMobile(String telephoneMobile) {
        this.telephoneMobile = telephoneMobile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Character getSexe() {
        return sexe;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getLibAge() {
        return libAge;
    }

    public void setLibAge(String libAge) {
        this.libAge = libAge;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Boolean getAgent() {
        return agent;
    }

    public void setAgent(Boolean agent) {
        this.agent = agent;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Personnel() {
    }

    public Personnel(Long id, @NotNull String firstname, @NotNull String emailaddress, String telephoneCisco, String telephoneMobile, @NotNull Long userId, @NotNull Character sexe, String fonction, String service, String libAge, Long organizationId, Boolean agent, String screenname) {
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
        this.screenname = screenname;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", emailaddress='" + emailaddress + '\'' +
                ", telephoneCisco='" + telephoneCisco + '\'' +
                ", telephoneMobile='" + telephoneMobile + '\'' +
                ", userId=" + userId +
                ", sexe=" + sexe +
                ", fonction='" + fonction + '\'' +
                ", service='" + service + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personnel personnel = (Personnel) o;
        return Objects.equals(id, personnel.id) && Objects.equals(firstname, personnel.firstname) && Objects.equals(emailaddress, personnel.emailaddress) && Objects.equals(telephoneCisco, personnel.telephoneCisco) && Objects.equals(telephoneMobile, personnel.telephoneMobile) && Objects.equals(userId, personnel.userId) && Objects.equals(sexe, personnel.sexe) && Objects.equals(fonction, personnel.fonction) && Objects.equals(service, personnel.service) && Objects.equals(libAge, personnel.libAge) && Objects.equals(organizationId, personnel.organizationId) && Objects.equals(agent, personnel.agent) && Objects.equals(departement, personnel.departement) && Objects.equals(screenname, personnel.screenname) && Objects.equals(remplacements, personnel.remplacements) && Objects.equals(absences, personnel.absences) && Objects.equals(personnelJours, personnel.personnelJours) && Objects.equals(personnelNuits, personnel.personnelNuits) && Objects.equals(months_supervise, personnel.months_supervise) && Objects.equals(notifications, personnel.notifications) && Objects.equals(annonces, personnel.annonces) && Objects.equals(roles, personnel.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, emailaddress, telephoneCisco, telephoneMobile, userId, sexe, fonction, service, libAge, organizationId, agent, departement, screenname, remplacements, absences, personnelJours, personnelNuits, months_supervise, notifications, annonces);
    }
}