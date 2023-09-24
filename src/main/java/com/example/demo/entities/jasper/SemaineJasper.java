package com.example.demo.entities.jasper;

import java.util.ArrayList;
import java.util.List;

public class SemaineJasper{
    private String periode;
    private List<DayJasper> lundi = new ArrayList<>();
    private List<DayJasper> mardi = new ArrayList<>();
    private List<DayJasper> mercredi = new ArrayList<>();
    private List<DayJasper> jeudi = new ArrayList<>();
    private List<DayJasper> vendredi = new ArrayList<>();
    private List<DayJasper> samedi_jour = new ArrayList<>();
    private List<DayJasper> samedi_nuit = new ArrayList<>();
    private List<DayJasper> dimanche_jour = new ArrayList<>();
    private List<DayJasper> dimanche_nuit = new ArrayList<>();

    public SemaineJasper() {
    }

    public SemaineJasper(String periode, List<DayJasper> lundi, List<DayJasper> mardi, List<DayJasper> mercredi, List<DayJasper> jeudi, List<DayJasper> vendredi, List<DayJasper> samedi_jour, List<DayJasper> samedi_nuit, List<DayJasper> dimanche_jour, List<DayJasper> dimanche_nuit) {
        this.periode = periode;
        this.lundi = lundi;
        this.mardi = mardi;
        this.mercredi = mercredi;
        this.jeudi = jeudi;
        this.vendredi = vendredi;
        this.samedi_jour = samedi_jour;
        this.samedi_nuit = samedi_nuit;
        this.dimanche_jour = dimanche_jour;
        this.dimanche_nuit = dimanche_nuit;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public List<DayJasper> getLundi() {
        return lundi;
    }

    public void setLundi(List<DayJasper> lundi) {
        this.lundi = lundi;
    }

    public List<DayJasper> getMardi() {
        return mardi;
    }

    public void setMardi(List<DayJasper> mardi) {
        this.mardi = mardi;
    }

    public List<DayJasper> getMercredi() {
        return mercredi;
    }

    public void setMercredi(List<DayJasper> mercredi) {
        this.mercredi = mercredi;
    }

    public List<DayJasper> getJeudi() {
        return jeudi;
    }

    public void setJeudi(List<DayJasper> jeudi) {
        this.jeudi = jeudi;
    }

    public List<DayJasper> getVendredi() {
        return vendredi;
    }

    public void setVendredi(List<DayJasper> vendredi) {
        this.vendredi = vendredi;
    }

    public List<DayJasper> getSamedi_jour() {
        return samedi_jour;
    }

    public void setSamedi_jour(List<DayJasper> samedi_jour) {
        this.samedi_jour = samedi_jour;
    }

    public List<DayJasper> getSamedi_nuit() {
        return samedi_nuit;
    }

    public void setSamedi_nuit(List<DayJasper> samedi_nuit) {
        this.samedi_nuit = samedi_nuit;
    }

    public List<DayJasper> getDimanche_jour() {
        return dimanche_jour;
    }

    public void setDimanche_jour(List<DayJasper> dimanche_jour) {
        this.dimanche_jour = dimanche_jour;
    }

    public List<DayJasper> getDimanche_nuit() {
        return dimanche_nuit;
    }

    public void setDimanche_nuit(List<DayJasper> dimanche_nuit) {
        this.dimanche_nuit = dimanche_nuit;
    }
}