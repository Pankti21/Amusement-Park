package userAuthentication;

import database.IUserPersistence;

public class LoginRegister {
    private User user;

    public LoginRegister(User user){
        this.user = user;
    }

    public LoginRegister() {

    }

    public void login(IUserPersistence userPersistence){
        //check if email in db
        //if so compare pw
        //add user to context
    }

    public void register(IUserPersistence userPersistence){
        //check email format
        //check pw format
        //boolean password = validatePasswordFormat(String password);

        //check not in db
        //save to db
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
    public  boolean validatePasswordFormat(String userPassword)
    {
        boolean passwordValid = true;
        if (userPassword.length() < 8 || userPassword.length() > 12)
        {
            System.out.println("User password must be 8 characters in length and should be less than 12");
            passwordValid = false;
        }
        String oneUpperCaseChar = "(.*[A-Z].*)";
        if (!userPassword.matches(oneUpperCaseChar))
        {
            System.out.println("User password must have atleast one uppercase character");
            passwordValid = false;
        }
        String oneLowerCaseChar = "(.*[a-z].*)";
        if (!userPassword.matches(oneLowerCaseChar))
        {
            System.out.println("User password must have atleast one lowercase character");
            passwordValid = false;
        }
        String oneNumber = "(.*[0-9].*)";
        if (!userPassword.matches(oneNumber))
        {
            System.out.println("User password must have atleast one number");
            passwordValid = false;
        }
        String oneSpecialChars = "(.*[@,#,$,%].*$)";
        if (!userPassword.matches(oneSpecialChars))
        {
            System.out.println("User password must have atleast one special character among @#$%");
            passwordValid = false;
        }
        return passwordValid;
    }
}
