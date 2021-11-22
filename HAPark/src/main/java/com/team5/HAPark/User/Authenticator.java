package com.team5.HAPark.User;

<<<<<<< HEAD:HAPark/src/main/java/com/team5/HAPark/userAuthentication/Authenticator.java
import com.team5.HAPark.database.mysql.MySQLDatabase;
import com.team5.HAPark.database.mysql.MySQLUserPersistence;
=======
import database.mysql.MySQLDatabase;
import com.team5.HAPark.User.DAO.MySQLUserPersistence;
>>>>>>> 778932d59e2a554be39ea78c21e0641f84831d5d:HAPark/src/main/java/com/team5/HAPark/User/Authenticator.java

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import java.util.Collections;

//I referenced this source to create this class:
//https://www.baeldung.com/spring-security-multiple-auth-providers
public class Authenticator implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = new User();

        user.setEmail(email);
        user.setPassword(password);

        Login login = new Login(user);
        MySQLDatabase mySQLDatabase = new MySQLDatabase();

        if (login.login(new MySQLUserPersistence(mySQLDatabase))){
            mySQLDatabase.close();
            return new UsernamePasswordAuthenticationToken(email, password, Collections.emptyList());
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
