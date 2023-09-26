package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.entities.interfaces.PersonnelDay;
import com.example.demo.entities.jasper.DayJasper;
import com.example.demo.entities.jasper.MoisJasper;
import com.example.demo.entities.jasper.PlanningJasper;
import com.example.demo.entities.jasper.SemaineJasper;
import com.example.demo.utils.ZeroPadding;
import org.jfree.data.time.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class JasperService {

    @Autowired
    PlanningService planningService;

    public PlanningJasper generatePlanningJasper(Long id) {

        Planning planning = planningService.getModelById(id);
        PlanningJasper planningJasper = new PlanningJasper();
        if (planning != null) {

            Set<Month> monthSet = planning.getMonths();

            for (Month month : monthSet) {
                MoisJasper moisJasper = new MoisJasper();

                moisJasper.setSuperviseur(getOneString(month.getSuperviseur()));
                Set<Permanence> permanenceSet = month.getPermanences();
                List<Permanence> permanenceList = new ArrayList<>(permanenceSet);
                permanenceList.sort((Comparator.comparing(Permanence::getDate)));
                int parcoursPermanence = 0;
                int nbrSemaine = 0;
                System.out.println("\n\n\n permanenceList first permanence =>"+permanenceList.get(0)+"\n\n\n");

                for (Permanence permanence : permanenceList) {
                    parcoursPermanence = parcoursPermanence % 7;
                    if (parcoursPermanence == 0) {
                        SemaineJasper semaineJasper = new SemaineJasper();
                        String periode = ZeroPadding.paddingNumber(permanence.getDate().getDayOfMonth()) + "/" + permanence.getDate().getMonthValue() + " au ";
                        DayJasper dayJasper = getNormalDayJasper(permanence);
                        semaineJasper.getLundi().add(dayJasper);
                        semaineJasper.setPeriode(periode);

                        moisJasper.getSemaine().add(semaineJasper);
                    }
                    else if (parcoursPermanence > 0 && parcoursPermanence < 5) {
                        DayJasper dayJasper = getNormalDayJasper(permanence);

                        if (parcoursPermanence == 1) {
                            (moisJasper.getSemaine().get(nbrSemaine)).getMardi().add(dayJasper);
                        } else if (parcoursPermanence == 2) {
                            (moisJasper.getSemaine().get(nbrSemaine)).getMercredi().add(dayJasper);

                        } else if (parcoursPermanence == 3) {
                            (moisJasper.getSemaine().get(nbrSemaine)).getJeudi().add(dayJasper);

                        } else {
                            (moisJasper.getSemaine().get(nbrSemaine)).getVendredi().add(dayJasper);

                        }
                    }
                    else if (parcoursPermanence == 5) {
                        DayJasper dayJasper;
                        for (int k = 0; k < 2; k++) {
                            if (k == 0) {
                                Set<PersonnelJour> personnelJourSet = permanence.getPersonnelJours();
                                dayJasper = getDayJasper(personnelJourSet);
                                (moisJasper.getSemaine().get(nbrSemaine)).getSamedi_jour().add(dayJasper);
                            } else {
                                Set<PersonnelNuit> personnelNuitSet = permanence.getPersonnelNuits();
                                dayJasper = getDayJasper(personnelNuitSet);
                                (moisJasper.getSemaine().get(nbrSemaine)).getSamedi_nuit().add(dayJasper);
                            }
                        }


                    }
                    else if (parcoursPermanence == 6) {

                        String periode = (moisJasper.getSemaine().get(nbrSemaine)).getPeriode();
                        periode = periode + ZeroPadding.paddingNumber(permanence.getDate().getDayOfMonth()) + "/" + permanence.getDate().getMonthValue();
                        (moisJasper.getSemaine().get(nbrSemaine)).setPeriode(periode);
                        for (int k = 0; k < 2; k++) {
                            DayJasper dayJasper;
                            if (k == 0) {
                                Set<PersonnelJour> personnelJourSet = permanence.getPersonnelJours();
                                dayJasper = getDayJasper(personnelJourSet);
                                (moisJasper.getSemaine().get(nbrSemaine)).getDimanche_jour().add(dayJasper);
                            } else {
                                Set<PersonnelNuit> personnelNuitSet = permanence.getPersonnelNuits();
                                dayJasper = getDayJasper(personnelNuitSet);
                                (moisJasper.getSemaine().get(nbrSemaine)).getDimanche_nuit().add(dayJasper);
                            }

                        }
                    }

                    parcoursPermanence++;
                    if (parcoursPermanence == 7) {
                        nbrSemaine++;
                    }
                }


                planningJasper.getMois().add(moisJasper);
            }
        }

        return planningJasper;
    }

    public String getOneString(Personnel personnel) {
        if (personnel == null) return null;
        if(personnel.getFirstname().length()<=10) return  personnel.getFirstname().toUpperCase();
        return (personnel.getFirstname().toUpperCase().split(" "))[0];
    }

    public DayJasper getNormalDayJasper(Permanence permanence) {
        DayJasper dayJasper = new DayJasper();
        if (!Objects.equals(permanence.getType(), "ouvrable")) {
            Set<PersonnelNuit> personnelNuitSet = permanence.getPersonnelNuits();
            dayJasper = getDayJasper(personnelNuitSet);
        } else {
            Set<PersonnelJour> personnelJourSet = permanence.getPersonnelJours();
            dayJasper = getDayJasper(personnelJourSet);
            dayJasper.setType_jour(permanence.getType());

        }
        return dayJasper;
    }

    public <T extends PersonnelDay> DayJasper getDayJasper(Set<T> personnelDaySet) {
        DayJasper dayJasper = new DayJasper();

        int parcoursDay = 1;
        for (PersonnelDay personnelDay : personnelDaySet) {
            if (parcoursDay == 1) {
                dayJasper.setPerson1(getOneString(personnelDay.getPersonnel()));
            } else if (parcoursDay == 2) {
                dayJasper.setPerson2(getOneString(personnelDay.getPersonnel()));
            } else if (parcoursDay == 3) {
                dayJasper.setPerson3(getOneString(personnelDay.getPersonnel()));
            } else if (parcoursDay == 4) {
                dayJasper.setPerson4(getOneString(personnelDay.getPersonnel()));
            }
            parcoursDay++;
        }
        return dayJasper;
    }
}
