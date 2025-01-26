package java24;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class JEP497 {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // create Lattice-based DSA keys
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("ML-DSA-87");
        KeyPair signingKeyPair = keyPairGen.generateKeyPair();

        // encrypt a message
        byte[] encryptedMessage = "Hello, World!".getBytes();

        // generate a signature
        Signature signature = Signature.getInstance("ML-DSA");
        signature.initSign(signingKeyPair.getPrivate());
        signature.update(encryptedMessage);
        byte[] signatureBytes = signature.sign();
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signatureBytes));

        // verify the signature
        Signature verifier = Signature.getInstance("ML-DSA");
        verifier.initVerify(signingKeyPair.getPublic());
        verifier.update(encryptedMessage);
        boolean isSignatureValid = verifier.verify(signatureBytes);
        System.out.println("Is signature valid? " + isSignatureValid);

    }
}
