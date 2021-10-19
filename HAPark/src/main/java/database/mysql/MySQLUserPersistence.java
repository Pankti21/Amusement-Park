package database.mysql;

import database.IUserPersistence;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class MySQLUserPersistence implements IUserPersistence {
    MySQLDatabase mySQLDatabase;

    public MySQLUserPersistence(MySQLDatabase mySQLDatabase){
        this.mySQLDatabase = mySQLDatabase;
    }

    @Override
    public void saveUser(String firstName, String lastName, String email, String pw) {
    }

    @Override
    public void updateUserInfo(String firstName, String lastName, String email, String pw) {

    }

    @Override
    public boolean doesUserExist(String email) {
        CallableStatement statement = null;
        boolean userExists = false;

        try {
            statement = mySQLDatabase.getConnection().prepareCall("{call does_user_exist(?,?)} ");
            statement.setString(1,email);
            statement.registerOutParameter(2, Types.BOOLEAN);
            statement.execute();
            userExists = statement.getBoolean(2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return userExists;
    }

    @Override
    public String getPassword(String email) {
        CallableStatement statement = null;
        String password = null;

        try {
            statement = mySQLDatabase.getConnection().prepareCall("{call get_password_for_user(?,?)} ");
            statement.setString(1,email);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.execute();
            password = statement.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return password;
    }
}
