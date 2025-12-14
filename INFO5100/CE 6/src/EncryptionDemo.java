import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.*;
import java.util.Base64;

class Person {
    String name;
    PublicKey publicKey;
    PrivateKey privateKey;

    Person(String name) throws Exception {
        this.name = name;
        generateKeys();
    }

    private void generateKeys() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        publicKey = pair.getPublic();
        privateKey = pair.getPrivate();
    }
}

public class EncryptionDemo {

    public static void main(String[] args) throws Exception {
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");

        System.out.println("----- AES-256 GCM Symmetric Encryption -----");
        runAesDemo(alice, bob);

        System.out.println("\n----- RSA-2048 Asymmetric Encryption -----");
        runRsaDemo(alice, bob);

        System.out.println("\n----- RSA Digital Signature -----");
        runSignatureDemo(alice, bob);
    }

    private static void runAesDemo(Person alice, Person bob) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey aesKey = keyGen.generateKey();

        String msg = "Hello Bob, this is Alice using AES!";
        byte[] iv = SecureRandom.getInstanceStrong().generateSeed(12);

        Cipher enc = Cipher.getInstance("AES/GCM/NoPadding");
        enc.init(Cipher.ENCRYPT_MODE, aesKey, new GCMParameterSpec(128, iv));
        byte[] encrypted = enc.doFinal(msg.getBytes());

        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));

        Cipher dec = Cipher.getInstance("AES/GCM/NoPadding");
        dec.init(Cipher.DECRYPT_MODE, aesKey, new GCMParameterSpec(128, iv));
        byte[] decrypted = dec.doFinal(encrypted);

        System.out.println("Decrypted: " + new String(decrypted));
    }

    private static void runRsaDemo(Person alice, Person bob) throws Exception {
        String msg = "Hello Bob, here is a message encrypted with your RSA key.";

        Cipher enc = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        enc.init(Cipher.ENCRYPT_MODE, bob.publicKey);
        byte[] encrypted = enc.doFinal(msg.getBytes());

        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));

        Cipher dec = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        dec.init(Cipher.DECRYPT_MODE, bob.privateKey);
        byte[] decrypted = dec.doFinal(encrypted);

        System.out.println("Decrypted: " + new String(decrypted));
    }

    private static void runSignatureDemo(Person alice, Person bob) throws Exception {
        String msg = "Bob, Alice is signing this message.";

        Signature signer = Signature.getInstance("SHA256withRSA");
        signer.initSign(alice.privateKey);
        signer.update(msg.getBytes());
        byte[] signature = signer.sign();

        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));

        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(alice.publicKey);
        verifier.update(msg.getBytes());

        boolean valid = verifier.verify(signature);
        System.out.println("Signature valid: " + valid);
    }
}
