import java.util.Scanner;

public class PlayfairCipher {
    private static final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ"; // Without 'J'

    private static String prepareMessage(String message) {
        return message.replaceAll("\\s", "").toUpperCase(); // Remove spaces and convert to uppercase
    }

    private static String encrypt(String message, String key) {
        StringBuilder encryptedMessage = new StringBuilder();
        String keyMatrix = generateKeyMatrix(key);

        for (int i = 0; i < message.length(); i += 2) {
            char first = message.charAt(i);
            char second = (i + 1 < message.length()) ? message.charAt(i + 1) : 'X';
            encryptedMessage.append(encryptPair(first, second, keyMatrix));
        }
        return encryptedMessage.toString();
    }

    private static String decrypt(String message, String key) {
        StringBuilder decryptedMessage = new StringBuilder();
        String keyMatrix = generateKeyMatrix(key);

        for (int i = 0; i < message.length(); i += 2) {
            char first = message.charAt(i);
            char second = message.charAt(i + 1);
            decryptedMessage.append(decryptPair(first, second, keyMatrix));
        }
        return decryptedMessage.toString();
    }

    private static String generateKeyMatrix(String key) {
        String keyWithoutDuplicates = key.replaceAll("J", "I").toUpperCase().replaceAll("\\s", "");
        keyWithoutDuplicates += ALPHABET.replaceAll("(.)(?=.*\\1)", "");
        return keyWithoutDuplicates;
    }

    private static String encryptPair(char first, char second, String keyMatrix) {
        int index1 = keyMatrix.indexOf(first);
        int index2 = keyMatrix.indexOf(second);

        int row1 = index1 / 5, col1 = index1 % 5;
        int row2 = index2 / 5, col2 = index2 % 5;

        return (row1 == row2) ? "" + keyMatrix.charAt(row1 * 5 + (col1 + 1) % 5) + keyMatrix.charAt(row1 * 5 + (col2 + 1) % 5) :
                (col1 == col2) ? "" + keyMatrix.charAt(((row1 + 1) % 5) * 5 + col1) + keyMatrix.charAt(((row2 + 1) % 5) * 5 + col2) :
                        "" + keyMatrix.charAt(row1 * 5 + col2) + keyMatrix.charAt(row2 * 5 + col1);
    }

    private static String decryptPair(char first, char second, String keyMatrix) {
        int index1 = keyMatrix.indexOf(first);
        int index2 = keyMatrix.indexOf(second);

        int row1 = index1 / 5, col1 = index1 % 5;
        int row2 = index2 / 5, col2 = index2 % 5;

        return (row1 == row2) ? "" + keyMatrix.charAt(row1 * 5 + (col1 + 4) % 5) + keyMatrix.charAt(row1 * 5 + (col2 + 4) % 5) :
                (col1 == col2) ? "" + keyMatrix.charAt(((row1 + 4) % 5) * 5 + col1) + keyMatrix.charAt(((row2 + 4) % 5) * 5 + col2) :
                        "" + keyMatrix.charAt(row1 * 5 + col2) + keyMatrix.charAt(row2 * 5 + col1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        System.out.print("Enter the message: ");
        String message = scanner.nextLine();

        String preparedMessage = prepareMessage(message);
        String encryptedMessage = encrypt(preparedMessage, key);
        String decryptedMessage = decrypt(encryptedMessage, key);

        System.out.println("Prepared Message: " + preparedMessage);
        System.out.println("Encrypted Message: " + encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
        
        scanner.close();
    }
}
