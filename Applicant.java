package com.example.Ajiri;

public class Applicant {

    private String firstN, Ag, idNo, phoneN, location, emailAdd, describe;


    public Applicant(String firstN, String Ag, String idNo, String phoneN, String location, String emailAdd, String describe) {
        this.firstN = firstN;
        this.Ag = Ag;
        this.idNo = idNo;
        this.phoneN = phoneN;
        this.location = location;
        this.emailAdd = emailAdd;
        this.describe = describe;
    }

    public String getFirstN() {
        return firstN;
    }

    public String getAg() {
        return Ag;
    }

    public String getIdNo() {
        return idNo;
    }

    public String getPhoneN() {
        return phoneN;
    }

    public String getLocation() {
        return location;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public String getDescribe() {
        return describe;
    }
}
