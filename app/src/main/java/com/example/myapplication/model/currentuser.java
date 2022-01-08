package com.example.myapplication.model;

public class currentuser {


        public String username;
        public String email;
        public String avatar;



        public currentuser(String username, String email,String avatar) {
            this.username = username;
            this.email = email;
            this.avatar = avatar;
        }
        public currentuser(){

        }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
