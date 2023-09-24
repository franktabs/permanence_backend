package com.example.demo.entities.jasper;

public class DayJasper{
    private String person1;
    private String person2;
    private String person3;
    private String person4;

    public DayJasper() {
    }

    public DayJasper(String person1, String person2, String person3, String person4) {
        this.person1 = person1;
        this.person2 = person2;
        this.person3 = person3;
        this.person4 = person4;
    }

    public String getPerson1() {
        return person1;
    }

    public void setPerson1(String person1) {
        this.person1 = person1;
    }

    public String getPerson2() {
        return person2;
    }

    public void setPerson2(String person2) {
        this.person2 = person2;
    }

    public String getPerson3() {
        return person3;
    }

    public void setPerson3(String person3) {
        this.person3 = person3;
    }

    public String getPerson4() {
        return person4;
    }

    public void setPerson4(String person4) {
        this.person4 = person4;
    }
}