package com.team5.HAPark.user;

public class NewUser extends User{

    private String firstName;
    private String lastName;
    private String confirmedPassword;

    public NewUser(String email, String password, String confirmedPassword, String firstName, String lastName) {
        super(email,password);
        this.confirmedPassword = confirmedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
