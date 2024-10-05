package org.example.methods;

import java.util.HashMap;
import java.util.Map;

public class SimpleSubstitutionCipher {

    public static String encrypt(String plaintext, String alphabet, String substitution) {
        validateInputs(plaintext, alphabet, substitution);
        Map<Character, Character> substitutionMap = createSubstitutionMap(alphabet, substitution);
        StringBuilder encryptedText = new StringBuilder();

        for (char character : plaintext.toCharArray()) {
            if (substitutionMap.containsKey(character)) {
                encryptedText.append(substitutionMap.get(character));
            } else {
                encryptedText.append(character); //символ не в алфавите,без изменений
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String ciphertext, String alphabet, String substitution) {
        validateInputs(ciphertext, alphabet, substitution);
        Map<Character, Character> reverseSubstitutionMap = createSubstitutionMap(substitution, alphabet);
        StringBuilder decryptedText = new StringBuilder();

        for (char character : ciphertext.toCharArray()) {
            if (reverseSubstitutionMap.containsKey(character)) {
                decryptedText.append(reverseSubstitutionMap.get(character));
            } else {
                decryptedText.append(character); //символ не в алфавите,без изменений
            }
        }

        return decryptedText.toString();
    }

    private static Map<Character, Character> createSubstitutionMap(String from, String to) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < from.length(); i++) {
            map.put(from.charAt(i), to.charAt(i));
        }
        return map;
    }

    private static void validateInputs(String text, String alphabet, String substitution) {
        if (alphabet == null || alphabet.isEmpty()) {
            throw new IllegalArgumentException("Алфавит не может быть пустым.");
        }
        if (substitution == null || substitution.isEmpty()) {
            throw new IllegalArgumentException("Замена не может быть пустой.");
        }
        if (alphabet.length() != substitution.length()) {
            throw new IllegalArgumentException("Алфавит и замена должны быть одинаковой длины.");
        }
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Текст не может быть пустым.");
        }
    }
}