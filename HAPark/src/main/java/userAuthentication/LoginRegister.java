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
        return false;
    }

    public boolean validatePasswordFormat(){
        return false;
    }

}
