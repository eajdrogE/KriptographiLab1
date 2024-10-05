package org.example.methods;

public class AffineCipher {

    public static String decrypt(String plaintext, int a, int b, String alphabet) {
        validateInputs(plaintext, a, b, alphabet);
        StringBuilder encryptedText = new StringBuilder();
        int m = alphabet.length();

        for (char character : plaintext.toCharArray()) {
            int index = alphabet.indexOf(character);
            if (index != -1) {
                int encryptedIndex = (a * index + b) % m;
                encryptedText.append(alphabet.charAt(encryptedIndex));
            } else {
                encryptedText.append(character); // если символ не в алфавите, оставляем его без изменений
            }
        }

        return encryptedText.toString();
    }

    public static String encrypt(String ciphertext, int a, int b, String alphabet) {
        validateInputs(ciphertext, a, b, alphabet);
        StringBuilder decryptedText = new StringBuilder();
        int m = alphabet.length();
        int aInverse = modInverse(a, m);

        for (char character : ciphertext.toCharArray()) {
            int index = alphabet.indexOf(character);
            if (index != -1) {
                int decryptedIndex = (aInverse * (index - b + m)) % m;
                decryptedText.append(alphabet.charAt(decryptedIndex));
            } else {
                decryptedText.append(character); // если символ не в алфавите, оставляем его без изменений
            }
        }

        return decryptedText.toString();
    }

    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        throw new IllegalArgumentException("Multiplicative inverse for the given 'a' does not exist.");
    }

    private static void validateInputs(String text, int a, int b, String alphabet) {
        if (alphabet == null || alphabet.isEmpty()) {
            throw new IllegalArgumentException("Алфавит не может быть пустым.");
        }
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Текст не может быть пустым.");
        }
        if (gcd(a, alphabet.length()) != 1) {
            throw new IllegalArgumentException("Ключ 'a' должен быть взаимно простым с длиной алфавита.");
        }
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}