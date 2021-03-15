package com.example.jamsecure;

    public class FetchData
    {
        // Variable to store data corresponding
        // to firstname keyword in database
        private String sname;

        // Variable to store data corresponding
        // to lastname keyword in database
        private String phone;
        private String location;

        // Variable to store data corresponding
        // to age keyword in database
        private String jam_rate;
        private String semail;

        // Mandatory empty constructor
        // for use of FirebaseUI
        public FetchData() {}

        // Getter and setter method
        public String getSname()
        {
            return sname;
        }
        public String getLocation()
        {
            return location;
        }
        public String getSemail()
        {
            return semail;
        }
        public void setSname(String sname)
        {
            this.sname = sname;
        }
        public String getPhone()
        {
            return phone;
        }
        public void setPhone(String phone)
        {
            this.phone = phone;
        }
        public String getJam_rate()
        {
            return jam_rate;
        }
        public void setJam_rate(String jam_rate)
        {
            this.jam_rate = jam_rate;
        }
    }


