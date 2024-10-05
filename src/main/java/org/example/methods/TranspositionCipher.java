package org.example.methods;
import java.util.Arrays;

public class TranspositionCipher {

    public static String encrypt(String plaintext, String key) {
        validateInputs(plaintext, key);
        int[] keyOrder = getKeyOrder(key);
        int numRows = (int) Math.ceil((double) plaintext.length() / key.length());
        char[][] grid = new char[numRows][key.length()];

        // Заполнение сетки символами текста
        int k = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < key.length(); j++) {
                if (k < plaintext.length()) {
                    grid[i][j] = plaintext.charAt(k++);
                } else {
                    grid[i][j] = ' '; // Заполнение пустыми символами
                }
            }
        }

        // Шифрование
        StringBuilder encryptedText = new StringBuilder();
        for (int col : keyOrder) {
            for (int row = 0; row < numRows; row++) {
                encryptedText.append(grid[row][col]);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        validateInputs(ciphertext, key);
        int[] keyOrder = getKeyOrder(key);
        int numRows = (int) Math.ceil((double) ciphertext.length() / key.length());
        char[][] grid = new char[numRows][key.length()];

        // Заполнение сетки символами шифротекста
        int k = 0;
        for (int col : keyOrder) {
            for (int row = 0; row < numRows; row++) {
                if (k < ciphertext.length()) {
                    grid[row][col] = ciphertext.charAt(k++);
                } else {
                    grid[row][col] = ' '; // Заполнение пустыми символами
                }
            }
        }

        // Дешифрование
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < key.length(); j++) {
                decryptedText.append(grid[i][j]);
            }
        }

        return decryptedText.toString().trim();
    }

    private static int[] getKeyOrder(String key) {
        Character[] keyChars = new Character[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyChars[i] = key.charAt(i);
        }
        Arrays.sort(keyChars);

        int[] keyOrder = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyOrder[i] = Arrays.asList(keyChars).indexOf(key.charAt(i));
        }
        return keyOrder;
    }

    private static void validateInputs(String text, String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Ключ не может быть пустым.");
        }
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Текст не может быть пустым.");
        }
    }
}