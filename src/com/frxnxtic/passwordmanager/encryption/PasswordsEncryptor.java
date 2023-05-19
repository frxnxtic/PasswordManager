package com.frxnxtic.passwordmanager.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class PasswordsEncryptor {
    private static final String ENCRYPTION_ALGORITHM = "AES";

    private static final String osName = System.getProperty("os.name");
    private static final String userName = System.getProperty("user.name");
    private static final String javaVersion = System.getProperty("java.version");

    // Комбинирование системных параметров в одну строку
    private static final String combinedString = osName + userName + javaVersion;

    // Генерация секретного ключа на основе комбинированной строки
    private static final String SECRET_KEY = combinedString;

    public String encryptPassword(String password) throws Exception {
        SecretKeySpec secretKey = generateSecretKey();
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decryptPassword(String encryptedPassword) throws Exception {
        SecretKeySpec secretKey = generateSecretKey();
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static SecretKeySpec generateSecretKey() throws Exception {
        byte[] key = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // 128 bit = 16 bytes
        return new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
    }
}

