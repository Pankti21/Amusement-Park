package userAuthentication;

public class CurrentUser {
    private User user;
    private static CurrentUser currentUser;

    private CurrentUser(){}

    public static CurrentUser getInstance(){
        if (currentUser == null){
            currentUser = new CurrentUser();
        }
        return currentUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
