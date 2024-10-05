package org.example;

import org.example.methods.*;
import org.example.DataCarrier.DataInput;
import org.example.DataCarrier.DataOutput;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите вариант шифрования:");
        System.out.println("1. Шифр сдвига");
        System.out.println("2. Афинный шифр");
        System.out.println("3. Шифр простой замены");
        System.out.println("4. Шифр Хила");
        System.out.println("5. Шифр перестановки");
        System.out.println("6. Шифр Виженера");

        int cipherChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.println("Выберите операцию:");
        System.out.println("1. Шифрование");
        System.out.println("2. Расшифрование");

        int operationChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (cipherChoice) {
            case 1:

                if (operationChoice == 1){
                    String encryptedText = shiftCipher.encrypt(DataInput.inputText, DataInput.key, DataInput.alphabet);
                   // System.out.println("Зашифрованный текст: " + encryptedText);
                    DataOutput.printEncryptedText(encryptedText);
                }
                    else if (operationChoice == 2) {
                    String decryptedText = shiftCipher.decrypt(DataInput.inputText, DataInput.key, DataInput.alphabet);
                    //System.out.println("Расшифрованный текст: " + decryptedText);
                    DataOutput.printDecryptedText(decryptedText);
                }
                    else System.out.println("Некоректный ввод");
                break;
            case 2:
                String[] parts = DataInput.key.split(" ");
                int num1 = Integer.parseInt(parts[0]);
                int num2 = Integer.parseInt(parts[1]);
                if (operationChoice == 1){
                    String encryptedText = AffineCipher.encrypt(DataInput.inputText, num1, num2, DataInput.alphabet);
                    DataOutput.printEncryptedText(encryptedText);
                }
                else if (operationChoice == 2){String decryptedText = AffineCipher.decrypt(DataInput.inputText, num1, num2, DataInput.alphabet);
                    DataOutput.printDecryptedText(decryptedText);
                }
                else System.out.println("Некоректный ввод");
                break;
            case 3:
                if (operationChoice == 1){
                    String encryptedText = SimpleSubstitutionCipher.encrypt(DataInput.inputText, DataInput.alphabet, DataInput.key);
                    // System.out.println("Зашифрованный текст: " + encryptedText);
                    DataOutput.printEncryptedText(encryptedText);
                }
                else if (operationChoice == 2) {
                    String decryptedText = SimpleSubstitutionCipher.decrypt(DataInput.inputText, DataInput.alphabet, DataInput.key);
                    //System.out.println("Расшифрованный текст: " + decryptedText);
                    DataOutput.printDecryptedText(decryptedText);
                }
                else System.out.println("Некоректный ввод");
                break;
            case 4:
                if (operationChoice == 1){
                    String encryptedText = HillCipher.encrypt(DataInput.inputText, DataInput.key, DataInput.alphabet);
                    // System.out.println("Зашифрованный текст: " + encryptedText);
                    DataOutput.printEncryptedText(encryptedText);
                }
                else if (operationChoice == 2) {
                    String decryptedText = HillCipher.decrypt(DataInput.inputText, DataInput.key, DataInput.alphabet);
                    //System.out.println("Расшифрованный текст: " + decryptedText);
                    DataOutput.printDecryptedText(decryptedText);
                }
                else System.out.println("Некоректный ввод");
                break;
            case 5:
                if (operationChoice == 1){
                    String encryptedText = TranspositionCipher.encrypt(DataInput.inputText, DataInput.key);
                    // System.out.println("Зашифрованный текст: " + encryptedText);
                    DataOutput.printEncryptedText(encryptedText);
                }
                else if (operationChoice == 2) {
                    String decryptedText = TranspositionCipher.decrypt(DataInput.inputText, DataInput.key);
                    //System.out.println("Расшифрованный текст: " + decryptedText);
                    DataOutput.printDecryptedText(decryptedText);
                }
                else System.out.println("Некоректный ввод");
                break;
            case 6:
                if (operationChoice == 1){
                    String encryptedText = VigenereCipher.encrypt(DataInput.inputText, DataInput.key,DataInput.alphabet);
                    // System.out.println("Зашифрованный текст: " + encryptedText);
                    DataOutput.printEncryptedText(encryptedText);
                }
                else if (operationChoice == 2) {
                    String decryptedText = VigenereCipher.decrypt(DataInput.inputText, DataInput.key,DataInput.alphabet);
                    //System.out.println("Расшифрованный текст: " + decryptedText);
                    DataOutput.printDecryptedText(decryptedText);
                }
                else System.out.println("Некоректный ввод");
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }


    }
