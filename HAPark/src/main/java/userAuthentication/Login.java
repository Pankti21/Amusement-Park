package userAuthentication;

import database.IUserPersistence;

public class Login {
    private User user;

    public Login(User user) {
        this.user = user;
    }

    public boolean login(IUserPersistence userPersistence) {
        boolean loggedIn = false;
        if (userPersistence.doesUserExist(user.getEmail())) {
            String password = userPersistence.getPassword(user.getEmail());
            if (user.getPassword().equals(password)) {
                CurrentUser.getInstance().setUser(user);
                loggedIn = true;
            }
        }
        return loggedIn;
    }

    public void logout() {
        CurrentUser.getInstance().setUser(null);
    }

}
