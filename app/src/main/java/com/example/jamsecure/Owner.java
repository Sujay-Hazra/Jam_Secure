package com.example.jamsecure;

public class Owner {
    public String sname, semail, phone, location, jam_rate;
    public Owner(){

    }

    public Owner(String name, String email, String phone, String location, String jam_rate) {
        this.sname = name;
        this.semail = email;
        this.phone = phone;
        this.location = location;
        this.jam_rate= jam_rate;
    }
}