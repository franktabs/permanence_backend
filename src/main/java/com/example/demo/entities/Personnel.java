package com.example.demo.entities;

/*import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;*/
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.entities.interfaces.Model;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "personnel")
public class Personnel implements Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT UNSIGNED not null")
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
    @Column(name = "telephoneCisco", length = 45)
    private String telephoneCisco;

    @Size(max = 45)
    @Column(name = "telephoneMobile", length = 45)
    private String telephoneMobile;

    @NotNull
    @Column(name = "userId", nullable = false)
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
    @Column(name = "libAge")
    private String libAge;

    @Column(name = "organizationId")
    private Long organizationId;

    @Column(name = "agent")
    private Boolean agent;




    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

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

    @ManyToMany(mappedBy = "personnels", cascade = CascadeType.PERSIST)
    private Set<Role> roles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "recepteur", cascade = CascadeType.PERSIST)
    private Set<Notification> notifications = new LinkedHashSet<>();

    @OneToMany(mappedBy = "emetteur", cascade = CascadeType.PERSIST)
    private Set<Annonce> annonces = new LinkedHashSet<>();

    @Size(max = 255)
    @Column(name = "screenname")
    private String screenname;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Personnel personnel = (Personnel) o;
        return getId() != null && Objects.equals(getId(), personnel.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}