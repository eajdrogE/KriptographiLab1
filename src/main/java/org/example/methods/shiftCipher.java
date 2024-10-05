package org.example.methods;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class shiftCipher {
    public static String encrypt(String text, String key, String alphabet) {
        validateInputs(text, key, alphabet);
        int shift = Integer.parseInt(key.trim());
        StringBuilder result = new StringBuilder();
        int alphabetLength = alphabet.length();

        for (char character : text.toCharArray()) {
            int index = alphabet.indexOf(character);
            if (index != -1) {
                int shiftedIndex = (index + shift) % alphabetLength;
                result.append(alphabet.charAt(shiftedIndex));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, String key, String alphabet) {
        validateInputs(text, key, alphabet);
        int shift = Integer.parseInt(key.trim());
        StringBuilder result = new StringBuilder();
        int alphabetLength = alphabet.length();

        for (char character : text.toCharArray()) {
            int index = alphabet.indexOf(character);
            if (index != -1) {
                int shiftedIndex = (index - shift + alphabetLength) % alphabetLength;
                result.append(alphabet.charAt(shiftedIndex));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    private static void validateInputs(String text, String key, String alphabet) {
        if (alphabet == null || alphabet.isEmpty()) {
            throw new IllegalArgumentException("Алфавит не может быть пустым.");
        }
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Текст не может быть пустым.");
        }
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Ключ не может быть пустым.");
        }
        try {
            Integer.parseInt(key.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ключ должен быть числом.");
        }
    }
}