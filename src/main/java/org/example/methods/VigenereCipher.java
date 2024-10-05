package org.example.methods;

public class VigenereCipher {

    public static String encrypt(String plaintext, String key, String alphabet) {
        validateInputs(plaintext, key, alphabet);
        StringBuilder encryptedText = new StringBuilder();
        int keyIndex = 0;

        for (char character : plaintext.toCharArray()) {
            int charIndex = alphabet.indexOf(character);
            if (charIndex != -1) {
                int keyCharIndex = alphabet.indexOf(key.charAt(keyIndex % key.length()));
                int encryptedIndex = (charIndex + keyCharIndex) % alphabet.length();
                encryptedText.append(alphabet.charAt(encryptedIndex));
                keyIndex++;
            } else {
                encryptedText.append(character); // если символ не в алфавите, оставляем его без изменений
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String ciphertext, String key, String alphabet) {
        validateInputs(ciphertext, key, alphabet);
        StringBuilder decryptedText = new StringBuilder();
        int keyIndex = 0;

        for (char character : ciphertext.toCharArray()) {
            int charIndex = alphabet.indexOf(character);
            if (charIndex != -1) {
                int keyCharIndex = alphabet.indexOf(key.charAt(keyIndex % key.length()));
                int decryptedIndex = (charIndex - keyCharIndex + alphabet.length()) % alphabet.length();
                decryptedText.append(alphabet.charAt(decryptedIndex));
                keyIndex++;
            } else {
                decryptedText.append(character); // если символ не в алфавите, оставляем его без изменений
            }
        }

        return decryptedText.toString();
    }

    private static void validateInputs(String text, String key, String alphabet) {
        if (alphabet == null || alphabet.isEmpty()) {
            throw new IllegalArgumentException("Алфавит не может быть пустым.");
        }
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Ключ не может быть пустым.");
        }
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Текст не может быть пустым.");
        }
        for (char c : key.toCharArray()) {
            if (alphabet.indexOf(c) == -1) {
                throw new IllegalArgumentException("Ключ содержит символы, отсутствующие в алфавите: " + c);
            }
        }
    }
}