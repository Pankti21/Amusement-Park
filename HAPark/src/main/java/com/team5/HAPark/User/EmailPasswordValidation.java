package com.team5.HAPark.User;

public class EmailPasswordValidation {

    private final User user;
    public EmailPasswordValidation(User user) {this.user = user;}

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
            System.out.println("User password must have at least one uppercase character");
            passwordValid = false;
        }
        String oneLowerCaseChar = "(.*[a-z].*)";
        if (!password.matches(oneLowerCaseChar))
        {
            System.out.println("User password must have at least one lowercase character");
            passwordValid = false;
        }
        String oneNumber = "(.*[0-9].*)";
        if (!password.matches(oneNumber))
        {
            System.out.println("User password must have at least one number");
            passwordValid = false;
        }
        String oneSpecialChars = "(.*[@#$%].*)";
        if (!password.matches(oneSpecialChars))
        {
            System.out.println("User password must have at least one special character among @#$%");
            passwordValid = false;
        }
        return passwordValid;
    }
}
