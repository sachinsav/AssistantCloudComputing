package com.dream.assistantcloudcomputing;


public class User {
    public String username,email,phone,pwd;
    public User(){

    }
    public User(String username,String email,String phone,String pwd){
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}

