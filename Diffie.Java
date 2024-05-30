import java.math.BigInteger;
import java.security.SecureRandom;

public class Diffie{

    public static void main(String[] args) {
        // Generate large prime number and base
        BigInteger p = BigInteger.probablePrime(512, new SecureRandom());
        BigInteger g = BigInteger.valueOf(2); // Base

        // Alice's private key
        BigInteger a = new BigInteger(512, new SecureRandom());

        // Bob's private key
        BigInteger b = new BigInteger(512, new SecureRandom());

        // Calculate Alice's public key
        BigInteger A = g.modPow(a, p);

        // Calculate Bob's public key
        BigInteger B = g.modPow(b, p);

        // Calculate the shared secret
        BigInteger secretA = B.modPow(a, p);
        BigInteger secretB = A.modPow(b, p);

        // Check if both secrets match
        if (secretA.equals(secretB)) {
            System.out.println("Key exchange successful!");
            System.out.println("Shared Secret: " + secretA);
        } else {
            System.out.println("Error: Secret keys do not match!");
        }
    }
}
