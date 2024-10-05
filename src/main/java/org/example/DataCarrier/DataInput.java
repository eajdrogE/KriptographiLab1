package org.example.DataCarrier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class DataInput {
    public static String alphabet;
    public static String inputText;
    public static String key;

    static {
        try {
            // Чтение файла с алфавитом
            alphabet = new String(Files.readAllBytes(Paths.get("alphabet.txt")));
            System.out.println("Alphabet: " + alphabet);

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
}