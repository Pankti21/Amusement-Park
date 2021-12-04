package com.team5.HAPark.user;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmedPassword;
    private String reconfirmedPassword;
    public User(){}

    public User(String confirmedPassword, String reconfirmedPassword) {
        this.password = confirmedPassword;
        this.confirmedPassword = reconfirmedPassword;
    }
    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getReconfirmedPassword() {
        return reconfirmedPassword;
    }

    public void setReconfirmedPassword(String reconfirmedPassword) {
        this.reconfirmedPassword = reconfirmedPassword;
    }
}
