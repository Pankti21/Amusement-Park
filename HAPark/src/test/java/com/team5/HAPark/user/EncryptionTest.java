package com.team5.HAPark.user;

import com.team5.HAPark.user.model.Encryption;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    @Test
    void encryptPassword() throws NoSuchAlgorithmException {
        String encryptedPassword = Encryption.encryptPassword("encryptiontest");
        assertEquals("5de51e6e7e3fdb7ea67b63c82907378126fbd892c02e128a6d89a56416346729",encryptedPassword);
    }
}