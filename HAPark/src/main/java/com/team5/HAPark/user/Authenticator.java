package com.team5.HAPark.user;

import com.team5.HAPark.database.mysql.MySQLDatabase;
import com.team5.HAPark.user.DAO.MySQLUserPersistence;
import com.team5.HAPark.user.DAO.UserPersistenceFactory;
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
        MySQLDatabase mySQLDatabase =  MySQLDatabase.getInstance();

        if (login.login(new UserPersistenceFactory().createUserPersistence())){
            mySQLDatabase.close();
            return new UsernamePasswordAuthenticationToken(email, password, Collections.emptyList());
        } else {
            mySQLDatabase.close();
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
