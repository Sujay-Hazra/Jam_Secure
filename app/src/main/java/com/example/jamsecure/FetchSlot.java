package com.example.jamsecure;

public class FetchSlot
{
    // Variable to store data corresponding
    // to firstname keyword in database
    private String JamRoom_Email;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String Jam_Date;
    private String Jam_Time;
    private String Start_at;
    private String End_at;

    // Variable to store data corresponding
    // to age keyword in database
    private String Duration;
    private String User_Email;


    // Mandatory empty constructor
    // for use of FirebaseUI
    public FetchSlot() {}

    public String getStart_at() {
        return Start_at;
    }

    public void setStart_at(String start_at) {
        this.Start_at = start_at;
    }

    public String getEnd_at() {
        return End_at;
    }

    public void setEnd_at(String end_at) {
        this.End_at = end_at;
    }

    public String getJamRoom_Email() {
        return JamRoom_Email;
    }

    public void setJamRoom_Email(String jamRoom_Email) {
        this.JamRoom_Email = jamRoom_Email;
    }

    public String getJam_Date() {
        return Jam_Date;
    }

    public void setJam_Date(String jam_date) {
        this.Jam_Date = jam_date;
    }

    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String user_Email) {
        this.User_Email = user_Email;
    }

    public String getJam_Time() {
        return Jam_Time;
    }

    public void setJam_Time(String jam_Time) {
        this.Jam_Time = jam_Time;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        this.Duration = duration;
    }

    // Getter and setter method

}