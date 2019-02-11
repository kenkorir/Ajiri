package com.example.Ajiri;

public class Client {
    private String name, Email, Location, Id_No;

    public Client(String name, String Email, String Location, String Id_No){
        this.name = name;
        this.Email = Email;
        this.Location = Location;
        this.Id_No = Id_No;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public String getLocation() {
        return Location;
    }

    public String getId_No() {
        return Id_No;
    }
}
