import java.security.*;
import java.util.Base64;

public class DigitalSignatureExample {

    public static void main(String[] args) {
        try {
            // Generate key pair
            KeyPair keyPair = generateKeyPair();

            // Get private and public keys
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Message to be signed
            String message = "Hello, this is a signed message.";

            // Signing the message
            byte[] signature = sign(message, privateKey);
            System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));

            // Verifying the signature
            boolean isVerified = verify(message, signature, publicKey);
            System.out.println("Signature verified: " + isVerified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // You can choose your key size
        return keyPairGenerator.generateKeyPair();
    }

    private static byte[] sign(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        return signature.sign();
    }

    private static boolean verify(String message, byte[] signature, PublicKey publicKey) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(message.getBytes());
        return verifier.verify(signature);
    }
}