package database;

public interface IUserPersistence {
    public void saveUser(String firstName, String lastName, String email, String pw);
    public void updateUserInfo(String firstName, String lastName, String email, String pw);
    public boolean doesUserExist(String email);
    public String getPassword(String email);
}
