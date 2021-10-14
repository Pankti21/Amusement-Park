package database;

public interface IUserPersistence {
    public void saveUser(String firstName, String lastName, String email, String pw);
    public void updateUserInfo(String firstName, String lastName, String email, String pw);
}
