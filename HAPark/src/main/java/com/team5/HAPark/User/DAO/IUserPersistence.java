<<<<<<< HEAD:HAPark/src/main/java/com/team5/HAPark/database/mysql/IUserPersistence.java
package com.team5.HAPark.database.mysql;
=======
package com.team5.HAPark.User.DAO;
>>>>>>> 778932d59e2a554be39ea78c21e0641f84831d5d:HAPark/src/main/java/com/team5/HAPark/User/DAO/IUserPersistence.java

import com.team5.HAPark.User.User;

import java.sql.SQLException;

public interface IUserPersistence {

    void saveUser(String email, String firstName, String lastName, String pw) throws SQLException;

    boolean doesUserExist(String email) throws SQLException;

    String getPassword(String email) throws SQLException;

    User loadUser(String email) throws SQLException;

    void userUpdatedPassword(String confirmedPassword, String email) throws SQLException;
}
