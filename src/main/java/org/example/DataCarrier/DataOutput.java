package org.example.DataCarrier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DataOutput {
    public static void main(String[] args) {
        // Пример использования методов
    }

    // Метод для вывода информации из файла decrypt.txt
    public static void printDecryptedText(String a) {
        try {
            Files.write(Paths.get("decrypt.txt"), new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get("decrypt.txt"), a.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            String decryptedText = new String(Files.readAllBytes(Paths.get("decrypt.txt")));
            System.out.println("Decrypted Text: " + decryptedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для вывода информации из файла crypt.txt
    public static void printEncryptedText(String a) {
        try {
            Files.write(Paths.get("crypt.txt"), new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
            Files.write(Paths.get("crypt.txt"), a.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            String encryptedText = new String(Files.readAllBytes(Paths.get("crypt.txt")));
            System.out.println("Encrypted Text: " + encryptedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
