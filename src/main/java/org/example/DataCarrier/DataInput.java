package org.example.DataCarrier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
public class DataInput {
    public static String alphabet;
    public static String inputText;
    public static String key;

    static {
        try {
            // Чтение файла с алфавитом
            alphabet = new String(Files.readAllBytes(Paths.get("alphabet.txt")));
            System.out.println("Alphabet: " + alphabet);
            validateAlphabet(alphabet);
            // Чтение файла с открытым текстом / шифртекстом
            inputText = new String(Files.readAllBytes(Paths.get("in.txt")));
            System.out.println("Input Text: " + inputText);

            // Чтение файла с ключом
            key = new String(Files.readAllBytes(Paths.get("key.txt")));
            System.out.println("Key: " + key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void validateAlphabet(String alphabet) {
        if (alphabet == null || alphabet.isEmpty()) {
            throw new IllegalArgumentException("Алфавит не может быть пустым.");
        }
        if (hasDuplicateCharacters(alphabet)) {
            throw new IllegalArgumentException("Алфавит не должен содержать повторяющихся символов.");
        }
    }

    private static boolean hasDuplicateCharacters(String str) {
        Set<Character> charSet = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (!charSet.add(c)) {
                return true;
            }
        }
        return false;
    }
}