package database;

public class MySQLUserPersistence implements IUserPersistence {

    @Override
    public void saveUser(String firstName, String lastName, String email, String pw) {
    }

    @Override
    public void updateUserInfo(String firstName, String lastName, String email, String pw) {

    }

    @Override
    public boolean doesUserExist(String email) {
        return false;
    }

    @Override
    public String getPassword(String email) {
        return null;
    }
}
