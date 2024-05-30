import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DESEncryption {

    private static final String DES_ALGORITHM = "DES";
    private static final String DES_TRANSFORMATION = "DES/ECB/PKCS5Padding"; // ECB mode for simplicity

    public static void main(String[] args) throws Exception {
        // Pre-shared secret key (64-bit key for DES)
        String secretKey = "seckey01"; // 8 characters long

        // Message to be sent by Bob
        String message = "Hi, Alice@123";

        // Encrypt the message
        byte[] encryptedBytes = encrypt(message, secretKey);
        String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Message sent by Bob: " + encryptedMessage);

        // Decrypt the message received by Alice
        String decryptedMessage = decrypt(encryptedBytes, secretKey);
        System.out.println("Decrypted Message received by Alice: " + decryptedMessage);
    }

    public static byte[] encrypt(String plainText, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), DES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(DES_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(plainText.getBytes());
    }

    public static String decrypt(byte[] encryptedBytes, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), DES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(DES_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
