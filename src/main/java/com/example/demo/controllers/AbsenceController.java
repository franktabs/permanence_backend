package com.example.demo.controllers;

import com.example.demo.dto.AbsenceDto;
import com.example.demo.dto.PersonnelDto;
import com.example.demo.entities.Absence;
import com.example.demo.entities.Personnel;

import static com.example.demo.controllers.DepartementController.convertDepartementToDto;
import static com.example.demo.controllers.PersonnelController.convertPersonnelToDto;

public class AbsenceController {

    public static AbsenceDto convertAbsenceToDto(Absence absence, int depthPersonnel){
        AbsenceDto absenceDto = new AbsenceDto(
                absence.getId(),
                absence.getMessage(),
                absence.getMotif(),
                absence.getValidate(),
                absence.getSubmissionDate(),
                absence.getStart(),
                absence.getEnd(),
                absence.getType()
        );
        if (depthPersonnel > 0) {
            if (absence.getPersonnel()!= null) {
                absenceDto.setPersonnel(convertPersonnelToDto(absence.getPersonnel(), 1, depthPersonnel-1));
            }
        }
        return absenceDto;
    }
}
