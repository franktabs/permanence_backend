package com.afriland.dsi.permanence.entities.jasper;

import java.util.ArrayList;
import java.util.List;

public class MoisJasper{
    private String superviseur;
    private List<SemaineJasper> semaine = new ArrayList<>();

    public MoisJasper(String superviseur, List<SemaineJasper> semaine) {
        this.superviseur = superviseur;
        this.semaine = semaine;
    }

    public MoisJasper() {
    }

    public String getSuperviseur() {
        return superviseur;
    }

    public void setSuperviseur(String superviseur) {
        this.superviseur = superviseur;
    }

    public List<SemaineJasper> getSemaine() {
        return semaine;
    }

    public void setSemaine(List<SemaineJasper> semaine) {
        this.semaine = semaine;
    }
}
