package database;

public interface IUserPersistence {
    void saveUser(String firstName, String lastName, String email, String pw);
    void updateUserInfo(String firstName, String lastName, String email, String pw);
    boolean doesUserExist(String email);
    String getPassword(String email);
}
