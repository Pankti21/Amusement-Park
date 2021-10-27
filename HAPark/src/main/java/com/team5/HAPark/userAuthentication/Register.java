package com.team5.HAPark.userAuthentication;

import database.IUserPersistence;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Register {

    private User user;

    public Register(User user) {
        this.user = user;
    }

    public boolean register(IUserPersistence userPersistence, String confirmedPassword){

        if (user.getPassword()!=null && !user.getPassword().isEmpty()
                && user.getEmail()!=null && !user.getEmail().isEmpty()
                && user.getFirstName()!=null && !user.getFirstName().isEmpty()
                && user.getLastName()!=null && !user.getLastName().isEmpty()
                && confirmedPassword!=null){

            if (validateEmailFormat() && validatePasswordFormat()) {

                if (user.getPassword().matches(confirmedPassword)) {

                    try {
                        if (!userPersistence.doesUserExist(user.getEmail())) {
                            Encryption encryption = new Encryption();
                            userPersistence.saveUser(user.getEmail(), user.getFirstName(),
                                    user.getLastName(),encryption.encryptPassword(user.getPassword()));
                            return true;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    public boolean validateEmailFormat(){

        String email = user.getEmail();
        String[] emailSplit = email.split("@");

        if (emailSplit.length == 2 && emailSplit[0].length()<65){
            String[] domainSplit = emailSplit[1].split("\\.");
            if (domainSplit.length >= 2) {
                if (Character.toString(email.charAt(email.length()-1)).matches("[0-9a-zA-Z]")) {
                    return user.getEmail().matches("[0-9a-zA-Z][0-9a-zA-Z.!#$%&'*+-/=?^_`{|]*@[0-9a-zA-Z!#$%&'*+-/=?^_`{|]*.[0-9a-zA-Z.!#$%&'*+-/=?^_`{|]*");
                }
            }
        }

        return false;
    }

    /* Password validation */
    public  boolean validatePasswordFormat(){

        String password = user.getPassword();
        boolean passwordValid = true;

        if (password.length() < 8 || password.length() > 12)
        {
            System.out.println("User password must be 8 characters in length and should be less than 12");
            passwordValid = false;
        }
        String oneUpperCaseChar = "(.*[A-Z].*)";
        if (!password.matches(oneUpperCaseChar))
        {
            System.out.println("User password must have atleast one uppercase character");
            passwordValid = false;
        }
        String oneLowerCaseChar = "(.*[a-z].*)";
        if (!password.matches(oneLowerCaseChar))
        {
            System.out.println("User password must have atleast one lowercase character");
            passwordValid = false;
        }
        String oneNumber = "(.*[0-9].*)";
        if (!password.matches(oneNumber))
        {
            System.out.println("User password must have atleast one number");
            passwordValid = false;
        }
        String oneSpecialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(oneSpecialChars))
        {
            System.out.println("User password must have atleast one special character among @#$%");
            passwordValid = false;
        }
        return passwordValid;
    }
}