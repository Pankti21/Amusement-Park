package database;

import com.team5.HAPark.userAuthentication.User;

import java.sql.SQLException;

public interface IUserPersistence {

    void saveUser(String firstName, String lastName, String email, String pw) throws SQLException;

    void updateUserInfo(String firstName, String lastName, String email, String pw);

    boolean doesUserExist(String email) throws SQLException;

    String getPassword(String email) throws SQLException;

    User loadUser(String email) throws SQLException;

}
