package com.example.jamsecure;

public class FetchData {
    String sname;
    String jam_rate;
    String phone;
    public FetchData(){}
    public FetchData( String sname, String jam_rate, String phone) {
        this.sname = sname;
        this.jam_rate = jam_rate;
        this.phone = phone;
    }


    public String getName() {
        return sname;
    }

    public String getJamrate() {
        return jam_rate;
    }

    public String getMob() {
        return phone;
    }

}

