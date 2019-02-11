package com.example.Ajiri;

public class View {

    private  String Title;
    private int Pri;

    public View() {

        //empty contructor
    }

    public View(String title, int pri) {
        this.Title = title;
        this.Pri = pri;
    }

    public String getTitle() {
        return Title;
    }

    public int getPri() {
        return Pri;
    }

}

