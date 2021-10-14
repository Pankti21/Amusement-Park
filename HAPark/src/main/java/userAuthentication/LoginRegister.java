package userAuthentication;

import database.IUserPersistence;

public class LoginRegister {
    private User user;

    public LoginRegister(User user){
        this.user = user;
    }

    public void login(IUserPersistence userPersistence){
        //check if email in db
        //if so compare pw
        //add user to context
    }

    public void register(IUserPersistence userPersistence){
        //check email format
        //check pw format
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

    public boolean validatePasswordFormat(){
        return false;
    }

}
