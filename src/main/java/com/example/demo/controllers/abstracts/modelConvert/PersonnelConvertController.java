package com.example.demo.controllers.abstracts.modelConvert;

import com.example.demo.controllers.*;
import com.example.demo.controllers.abstracts.ModelConvertController;
import com.example.demo.dto.*;
import com.example.demo.entities.*;
import com.example.demo.services.PersonnelService;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.controllers.RemplacementController.convertRemplacementToDto;
import static com.example.demo.controllers.abstracts.modelConvert.AbsenceConvertController.convertAbsenceToDto;
import static com.example.demo.controllers.abstracts.modelConvert.DepartementConvertController.convertDepartementToDto;

public class PersonnelConvertController extends ModelConvertController<Personnel, PersonnelDto, PersonnelService> {
    @Override
    public PersonnelDto convertModelToDto(Personnel model, int... depth) {
        return convertPersonnelToDto(model, depth[0], depth[1], depth[2], depth[3], depth[4], depth[5], depth[6], depth[7], depth[8]);
    }

    @Override
    public Personnel convertDtoToModel(PersonnelDto modelDto) {
        return convertDtoToPersonnel(modelDto);
    }

    public static PersonnelDto convertPersonnelToDto(Personnel personnel, int depthDepartement, int depthAbsence, int depthRemplacement, int depthPersonnelNuit, int depthPersonnelJour, int depthMonthSupervise, int depthRole, int depthNotification, int depthAnnonce) {
        PersonnelDto personnelDto = new PersonnelDto(
                personnel.getId(),
                personnel.getFirstname(),
                personnel.getEmailaddress(),
                personnel.getTelephoneCisco(),
                personnel.getTelephoneMobile(),
                personnel.getUserId(),
                personnel.getSexe(),
                personnel.getFonction(),
                personnel.getService(),
                personnel.getLibAge(),
                personnel.getOrganizationId(),
                personnel.getAgent(),
                personnel.getScreenname()
        );
        if (depthDepartement > 0) {
            if (personnel.getDepartement() != null) {
                personnelDto.setDepartement(convertDepartementToDto(personnel.getDepartement(), 1, depthDepartement - 1));
            }
        }
        if (depthAbsence > 0) {

            Set<AbsenceDto> absenceDtos = new HashSet<>();
            if (personnel.getAbsences() != null) {

                for (Absence absence : personnel.getAbsences()) {
                    absenceDtos.add(convertAbsenceToDto(absence, depthAbsence - 1));
                }
            }

            personnelDto.setAbsences(absenceDtos);
        }
        if (depthRemplacement > 0) {

            Set<RemplacementDto> remplacementDtos = new HashSet<>();
            if (personnel.getRemplacements() != null) {

                for (Remplacement remplacement : personnel.getRemplacements()) {
                    remplacementDtos.add(convertRemplacementToDto(remplacement, depthRemplacement - 1, 1));
                }
            }

            personnelDto.setRemplacements(remplacementDtos);
        }

        if (depthPersonnelNuit > 0) {
            Set<PersonnelNuitDto> personnelNuitDtos = new HashSet<>();
            if (personnel.getPersonnelNuits() != null) {
                for (PersonnelNuit personnelNuit : personnel.getPersonnelNuits()) {
                    personnelNuitDtos.add(PersonnelNuitController.convertPersonnelNuitToDto(personnelNuit, 0, 0));
                }
            }

            personnelDto.setPersonnels_nuit(personnelNuitDtos);
        }

        if (depthPersonnelJour > 0) {
            Set<PersonnelJourDto> personnelJourDtos = new HashSet<>();
            if (personnel.getPersonnelJours() != null) {
                for (PersonnelJour personnelJour : personnel.getPersonnelJours()) {
                    personnelJourDtos.add(PersonnelJourController.convertPersonnelJourToDto(personnelJour, 0, 0));
                }
            }
            personnelDto.setPersonnels_jour(personnelJourDtos);
        }

        if (depthMonthSupervise > 0) {
            Set<MonthDto> monthDtos = new HashSet<>();
            if (personnel.getMonths_supervise() != null) {
                for (Month month : personnel.getMonths_supervise()) {
                    monthDtos.add(MonthController.convertMonthToDto(month, 0, 0, 0));
                }
            }

            personnelDto.setMonths_supervise(monthDtos);
        }

        if (depthRole > 0) {

            Set<RoleDto> roleDtos = new HashSet<>();
            if (personnel.getRoles() != null) {

                for (Role role : personnel.getRoles()) {
                    roleDtos.add(RoleController.convertRoleToDto(role, depthRole - 1));
                }
            }

            personnelDto.setRoles(roleDtos);
        }

        if (depthAnnonce > 0) {
            Set<AnnonceDto> annonceDtos = new HashSet<>();
            if (personnel.getAnnonces() != null) {

                for (Annonce annonce : personnel.getAnnonces()) {
                    annonceDtos.add(AnnonceController.convertAnnonceToDto(annonce, depthAnnonce - 1, 1));
                }
            }

            personnelDto.setAnnonces(annonceDtos);
        }
        if (depthNotification > 0) {
            Set<NotificationDto> notificationDtos = new HashSet<>();
            if (personnel.getNotifications() != null) {

                for (Notification notification : personnel.getNotifications()) {
                    notificationDtos.add(NotificationController.convertNotificationToDto(notification, depthNotification - 1, 1));
                }
            }

            personnelDto.setNotifications(notificationDtos);
        }

        return personnelDto;
    }

    public static Personnel convertDtoToPersonnel(PersonnelDto personnelDto) {
        Personnel personnel = new Personnel(
                personnelDto.getId(),
                personnelDto.getFirstname(),
                personnelDto.getEmailaddress(),
                personnelDto.getTelephoneCisco(),
                personnelDto.getTelephoneMobile(),
                personnelDto.getUserId(),
                personnelDto.getSexe(),
                personnelDto.getFonction(),
                personnelDto.getService(),
                personnelDto.getLibAge(),
                personnelDto.getOrganizationId(),
                personnelDto.getAgent(),
                personnelDto.getScreenname()
        );
        if (personnelDto.getDepartement() != null) {
            personnel.setDepartement(DepartementController.convertDtoToDepartement(personnelDto.getDepartement()));
        }
        Set<Absence> absences = new HashSet<>();
        if (personnelDto.getVacancies() != null) {
            for (AbsenceDto absenceDto : personnelDto.getVacancies()) {
                absences.add(AbsenceController.convertDtoToAbsence(absenceDto));
            }
        }
        personnel.setAbsences(absences);

        Set<Remplacement> remplacements = new HashSet<>();

        if (personnelDto.getAbsentList() != null) {

            for (RemplacementDto remplacementDto : personnelDto.getAbsentList()) {
                remplacements.add(RemplacementController.convertDtoToRemplacement(remplacementDto));
            }
        }
        personnel.setRemplacements(remplacements);

        Set<PersonnelJour> personnelJours = new HashSet<>();
        if (personnelDto.getPersonnels_jour() != null) {
            for (PersonnelJourDto personnelJourDto : personnelDto.getPersonnels_jour()) {
                personnelJours.add(PersonnelJourController.convertDtoToPersonnelJour(personnelJourDto));
            }
        }
        personnel.setPersonnelJours(personnelJours);

        Set<PersonnelNuit> personnelNuit = new HashSet<>();
        if (personnelDto.getPersonnels_nuit() != null) {
            for (PersonnelNuitDto personnelNuitDto : personnelDto.getPersonnels_nuit()) {
                personnelNuit.add(PersonnelNuitController.convertDtoToPersonnelNuit(personnelNuitDto));
            }
        }
        personnel.setPersonnelNuits(personnelNuit);

        Set<Month> months_supervise = new HashSet<>();
        if (personnelDto.getMonths_supervise() != null) {
            for (MonthDto monthDto : personnelDto.getMonths_supervise()) {
                months_supervise.add(MonthController.convertDtoToMonth(monthDto));
            }
        }
        personnel.setMonths_supervise(months_supervise);

        Set<Role> roles = new HashSet<>();
        if (personnelDto.getRoles() != null) {
            for (RoleDto roleDto : personnelDto.getRoles()) {
                roles.add(RoleController.convertDtoToRole(roleDto));
            }
        }
        personnel.setRoles(roles);

        Set<Annonce> annonces = new HashSet<>();
        if (personnelDto.getAnnonces() != null) {
            for (AnnonceDto annonceDto : personnelDto.getAnnonces()) {
                annonces.add(AnnonceController.convertDtoToAnnonce(annonceDto));
            }
        }
        personnel.setAnnonces(annonces);

        Set<Notification> notifications = new HashSet<>();
        if (personnelDto.getNotifications() != null) {
            for (NotificationDto notificationDto : personnelDto.getNotifications()) {
                notifications.add(NotificationController.convertDtoToNotification(notificationDto));
            }
        }
        personnel.setNotifications(notifications);

        return personnel;
    }
}
