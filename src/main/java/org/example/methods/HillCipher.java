package org.example.methods;


public class HillCipher {

    // Метод для получения матрицы ключа из строки
    public static int[][] getKeyMatrix(String key, int matrixSize, String alphabet) {
        int[][] keyMatrix = new int[matrixSize][matrixSize];
        int keyIndex = 0;

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (keyIndex < key.length()) {
                    char c = key.charAt(keyIndex);
                    int pos = alphabet.indexOf(c);
                    if (pos == -1) {
                        throw new IllegalArgumentException("Ключ содержит символы, отсутствующие в алфавите." + c);
                    }
                    keyMatrix[i][j] = pos;
                    keyIndex++;
                } else {
                    keyMatrix[i][j] = 0; // Дополнение нулями, если ключ короче необходимого
                }
            }
        }

        return keyMatrix;
    }

    // Метод для шифрования текста
    public static String encrypt(String plaintext, String key, String alphabet) {
        int matrixSize = (int) Math.sqrt(key.length());
        if (matrixSize * matrixSize != key.length()) {
            throw new IllegalArgumentException("Длина ключа должна быть квадратом целого числа.");
        }

        int[][] keyMatrix = getKeyMatrix(key, matrixSize, alphabet);
        int modulus = alphabet.length();

        if (plaintext.length() % matrixSize != 0) {
            int paddingLength = matrixSize - (plaintext.length() % matrixSize);
            char firstAlphabetChar = alphabet.charAt(0); // Первый символ алфавита
            for (int i = 0; i < paddingLength; i++) {
                plaintext += firstAlphabetChar; // Дополнение первым символом алфавита
            }
        }
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length()-1; i += matrixSize) {
            int[] textVector = new int[matrixSize];
            for (int j = 0; j < matrixSize; j++) {
                char c = plaintext.charAt(i + j);
                int pos = alphabet.indexOf(c);
                if (pos == -1) {
                    throw new IllegalArgumentException("Текст содержит символы, отсутствующие в алфавите."+ c);
                }
                textVector[j] = pos;
            }

            int[] encryptedVector = multiplyMatrixVector(keyMatrix, textVector, modulus);
            for (int value : encryptedVector) {
                ciphertext.append(alphabet.charAt(value));
            }
        }

        return ciphertext.toString();
    }

    // Метод для дешифрования текста
    public static String decrypt(String ciphertext, String key, String alphabet) {
        int matrixSize = (int) Math.sqrt(key.length());
        if (matrixSize * matrixSize != key.length()) {
            throw new IllegalArgumentException("Длина ключа должна быть квадратом целого числа.");
        }

        int[][] keyMatrix = getKeyMatrix(key, matrixSize, alphabet);
        int modulus = alphabet.length();

        int[][] inverseMatrix = inverseKeyMatrix(keyMatrix, modulus);
        if (inverseMatrix == null) {
            throw new IllegalArgumentException("Ключевая матрица необратима по модулю " + modulus + ".");
        }

        if (ciphertext.length() % matrixSize != 0) {
            throw new IllegalArgumentException("Длина шифротекста должна быть кратна размеру матрицы.");
        }

        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += matrixSize) {
            int[] textVector = new int[matrixSize];
            for (int j = 0; j < matrixSize; j++) {
                char c = ciphertext.charAt(i + j);
                int pos = alphabet.indexOf(c);
                if (pos == -1) {
                    throw new IllegalArgumentException("Шифротекст содержит символы, отсутствующие в алфавите.");
                }
                textVector[j] = pos;
            }

            int[] decryptedVector = multiplyMatrixVector(inverseMatrix, textVector, modulus);
            for (int value : decryptedVector) {
                plaintext.append(alphabet.charAt(value));
            }
        }

        return plaintext.toString();
    }

    // Вспомогательный метод для умножения матрицы на вектор
    private static int[] multiplyMatrixVector(int[][] matrix, int[] vector, int modulus) {
        int[] result = new int[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            long sum = 0;
            for (int col = 0; col < vector.length; col++) {
                sum += (long) matrix[row][col] * vector[col];
            }
            result[row] = (int) (sum % modulus);
            if (result[row] < 0) {
                result[row] += modulus;
            }
        }
        return result;
    }

    // Метод для получения обратной матрицы ключа
    public static int[][] inverseKeyMatrix(int[][] keyMatrix, int modulus) {
        int n = keyMatrix.length;
        int det = determinant(keyMatrix, modulus);
        if (det == 0) {
            return null;
        }

        det = mod(det, modulus);
        int detInv = modInverse(det, modulus);
        if (detInv == -1) {
            return null;
        }

        int[][] adj = adjugate(keyMatrix, modulus);
        if (adj == null) {
            return null;
        }

        int[][] inverse = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverse[i][j] = mod(adj[i][j] * detInv, modulus);
            }
        }

        return inverse;
    }

    // Метод для вычисления детерминанта матрицы по модулю
    private static int determinant(int[][] matrix, int modulus) {
        int n = matrix.length;
        int det = 1;
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, mat[i], 0, n);
        }

        for (int i = 0; i < n; i++) {
            // Поиск ненулевого элемента для разложения
            int pivot = -1;
            for (int row = i; row < n; row++) {
                if (mat[row][i] != 0) {
                    pivot = row;
                    break;
                }
            }

            if (pivot == -1) {
                return 0; // Детерминант равен нулю
            }

            if (pivot != i) {
                // Меняем строки местами
                int[] temp = mat[i];
                mat[i] = mat[pivot];
                mat[pivot] = temp;
                det = mod(-det, modulus);
            }

            det = mod(det * mat[i][i], modulus);
            int inv = modInverse(mat[i][i], modulus);
            if (inv == -1) {
                return 0; // Обратный элемент не существует
            }

            for (int j = i + 1; j < n; j++) {
                int factor = mod(mat[j][i] * inv, modulus);
                for (int k = i; k < n; k++) {
                    mat[j][k] = mod(mat[j][k] - factor * mat[i][k], modulus);
                }
            }
        }

        return det;
    }

    // Метод для вычисления матрицы присоединений (аджугат)
    private static int[][] adjugate(int[][] matrix, int modulus) {
        int n = matrix.length;
        int[][] adj = new int[n][n];

        if (n == 1) {
            adj[0][0] = 1;
            return adj;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sign = ((i + j) % 2 == 0) ? 1 : -1;
                int[][] minor = getMinor(matrix, i, j);
                int det = determinant(minor, modulus);
                adj[j][i] = mod(sign * det, modulus); // Транспонирование
            }
        }

        return adj;
    }

    // Метод для получения минора матрицы
    private static int[][] getMinor(int[][] matrix, int row, int col) {
        int n = matrix.length;
        int[][] minor = new int[n - 1][n - 1];
        int r = 0;

        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minor[r][c] = matrix[i][j];
                c++;
            }
            r++;
        }

        return minor;
    }

    // Метод для вычисления обратного элемента по модулю (расширенный алгоритм Евклида)
    private static int modInverse(int a, int modulus) {
        int m0 = modulus, t, q;
        int x0 = 0, x1 = 1;

        if (modulus == 1)
            return 0;

        a = mod(a, modulus);
        while (a > 1) {
            q = a / modulus;
            t = modulus;

            modulus = a % modulus;
            a = t;
            t = x0;

            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0)
            x1 += m0;

        return x1;
    }

    // Метод для вычисления остатка, учитывая отрицательные числа
    private static int mod(int a, int m) {
        int res = a % m;
        if (res < 0) {
            res += m;
        }
        return res;
    }
}