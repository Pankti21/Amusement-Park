package com.team5.HAPark.User;

import com.team5.HAPark.User.DAO.IUserPersistence;
import org.apache.catalina.security.SecurityConfig;
import org.junit.runners.model.FrameworkField;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

public class UpdateUserInformation {

    private User user;
    private EmailPasswordValidation emailPasswordValidation;
    public UpdateUserInformation(User user) {
        this.user = user;
    }

    public boolean updateUserInformation(IUserPersistence userPersistence, String confirmedPassword, HashMap<String, Object> mapper
    ) {
        String email = (String) mapper.get("email");
        String newPassword = (String) mapper.get("newPassword");
        String currentPassword = (String) mapper.get("currentPassword");


            if (currentUser == null) {
                throw new Exception("User not found");
            }

            if (user.getEmail(email) != null) {
                if (userService.findByEmail(email).getId() != currentUser.getId()) {
                    return new ResponseEntity("Email not found!", HttpStatus.BAD_REQUEST);
                }
            }

            if (userService.findByUsername(username) != null) {
                if (userService.findByUsername(username).getId() != currentUser.getId()) {
                    return new ResponseEntity("Username not found!", HttpStatus.BAD_REQUEST);
                }
            }

            SecurityConfig securityConfig = new SecurityConfig();


            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
            String dbPassword = currentUser.getPassword();

            if (null != currentPassword)
                if (passwordEncoder.matches(currentPassword, dbPassword)) {
                    if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
                        currentUser.setPassword(passwordEncoder.encode(newPassword));
                    }
                    currentUser.setEmail(email);
                } else {
                    return new ResponseEntity("Incorrect current password!", HttpStatus.BAD_REQUEST);
                }


            currentUser.setEmail(email);
            currentUser.setPassword(confirmedPassword);
            


            userService.save(currentUser);

            return new ResponseEntity("Update Success", HttpStatus.OK);
        }
    }

    /*  boolean loggedIn = false;

            if (emailPasswordValidation.validateEmailFormat() && emailPasswordValidation.validatePasswordFormat()) {

                if (user.getPassword().matches(confirmedPassword)) {

                    try {
                        if (!userPersistence.doesUserExist(user.getEmail())) {
                            Encryption encryption = new Encryption();
                            userPersistence.saveUser(user.getEmail(), user.getFirstName(),
                                    user.getLastName(), encryption.encryptPassword(user.getPassword()));
                            return true;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
            }
            return loggedIn;
        }*/