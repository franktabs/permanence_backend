package com.example.demo.entities.jasper;

import java.util.ArrayList;
import java.util.List;

public class PlanningJasper{
    private List<MoisJasper> mois = new ArrayList<>();

    public PlanningJasper() {
    }

    public PlanningJasper(List<MoisJasper> mois) {
        this.mois = mois;
    }

    public List<MoisJasper> getMois() {
        return mois;
    }

    public void setMois(List<MoisJasper> mois) {
        this.mois = mois;
    }
}