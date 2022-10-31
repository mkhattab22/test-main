package com.example.enroll.ui.login;

public class Instructor {

    private String username;
    private String password;
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instructor(String name, String username, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }
}
