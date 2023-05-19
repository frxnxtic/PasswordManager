package com.frxnxtic.passwordmanager.encryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class UserPasswordEncryptor {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    private byte[] salt;

    public UserPasswordEncryptor() {
        generateSalt();
    }

    private void generateSalt() {
        SecureRandom random = new SecureRandom();
        salt = new byte[16];
        random.nextBytes(salt);
    }

    public String encryptPassword(String password) {
        char[] passwordChars = password.toCharArray();
        byte[] hashedPassword = hashPassword(passwordChars, salt);
        return byteArrayToHexString(hashedPassword);
    }

    public boolean verifyPassword(String password, String encryptedPassword) {
        char[] passwordChars = password.toCharArray();
        byte[] hashedPassword = hashPassword(passwordChars, salt);
        String hashedPasswordString = byteArrayToHexString(hashedPassword);
        return hashedPasswordString.equals(encryptedPassword);
    }

    private byte[] hashPassword(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKey key = factory.generateSecret(spec);
            return key.getEncoded();
        } catch (Exception e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    private String byteArrayToHexString(byte[] array) {
        StringBuilder sb = new StringBuilder(array.length * 2);
        for (byte b : array) {
            int value = b & 0xff;
            if (value < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(value));
        }
        return sb.toString();
    }
}