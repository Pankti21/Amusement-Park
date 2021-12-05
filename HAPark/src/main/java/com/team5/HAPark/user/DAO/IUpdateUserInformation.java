package com.team5.HAPark.user.DAO;

import org.springframework.security.core.AuthenticationException;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface IUpdateUserInformation {
    public boolean updateUserPassword(IUserPersistence userPersistence,
                                      String oldPassword,String confirmedPassword, String reconfirmPassword)
            throws SQLException, NoSuchAlgorithmException, AuthenticationException;
}
