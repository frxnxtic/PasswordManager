package com.frxnxtic.passwordmanager.generator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


public class PasswordGenerator {
    public static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMERIC = "0123456789";
    public static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

    /**
     * Generating a password
     * @param length Length of a password
     * @param includeNumbers Use numbers?
     * @param includeUppercase Use Uppercase?
     * @param includeLowercase Use Lowercase?
     * @param includeSpecialCharacters Use Special Chars?
     * @return String
     */
    public static String generatePassword(int length, boolean includeUppercase, boolean includeLowercase,
                                          boolean includeNumbers, boolean includeSpecialCharacters) {
        List<String> characterSets = new ArrayList<>();
        if (includeUppercase) {
            characterSets.add(ALPHA_CAPS);
        }
        if (includeLowercase) {
            characterSets.add(ALPHA);
        }
        if (includeNumbers) {
            characterSets.add(NUMERIC);
        }
        if (includeSpecialCharacters) {
            characterSets.add(SPECIAL_CHARS);
        }

        if (characterSets.isEmpty()) {
            throw new IllegalArgumentException("At least one character set must be selected.");
        }

        StringBuilder password = new StringBuilder(length);
        SecureRandom random = new SecureRandom();

        characterSets.forEach(characterSet -> password.append(getRandomCharacter(characterSet, random)));

        for (int i = password.length(); i < length; i++) {
            String characterSet = characterSets.get(random.nextInt(characterSets.size()));
            password.append(getRandomCharacter(characterSet, random));
        }

        shufflePassword(password, random);

        return password.toString();
    }

    private static char getRandomCharacter(String characterSet, SecureRandom random) {
        int randomIndex = random.nextInt(characterSet.length());
        return characterSet.charAt(randomIndex);
    }

    private static void shufflePassword(StringBuilder password, SecureRandom random) {
        for (int i = 0; i < password.length(); i++) {
            int randomIndex = random.nextInt(password.length());
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(randomIndex));
            password.setCharAt(randomIndex, temp);
        }
    }
}
