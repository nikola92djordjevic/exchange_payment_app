package rs.payment.exchange.dto.security;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Simple implementation of AES cypher, to be used for encryption/decryption of text data.
 */
public class AESCipher {

    private static final int DEFAULT_IV_LENGTH = 16;
    private static final String DEFAULT_TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private static final String SECRET_KEY_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int SECRET_KEY_LENGTH = 256;

    private AESCipher() {
    }

    private static char[] generateRandomPassword(int passwordLength) {
        char[] randomPassword = new char[passwordLength];
        SecureRandom sr = new SecureRandom();
        for (int i = 0; i < passwordLength; i++) {
            randomPassword[i] = (char) (sr.nextInt('~' - '!' + 1) + '!');
        }
        return randomPassword;
    }

    public static String secretKeyToBase64(SecretKey secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public static SecretKey secretKeyFromBase64(String base64Encoded) {
        return new SecretKeySpec(Base64.getDecoder().decode(base64Encoded), "AES");
    }

    public static String encrypt(String rawText, int ivLength, String transformation, SecretKey secretKey) throws
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchPaddingException, NoSuchAlgorithmException {

        SecureRandom secureRandom = new SecureRandom();

        byte[] ivBytes = secureRandom.generateSeed(ivLength);
        AlgorithmParameterSpec algorithmParameterSpec = new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, algorithmParameterSpec);
        byte[] encryptedMessageBytes = cipher.doFinal(rawText.getBytes(StandardCharsets.UTF_8));

        byte[] bytesToEncode = new byte[ivBytes.length + encryptedMessageBytes.length];
        System.arraycopy(ivBytes, 0, bytesToEncode, 0, ivBytes.length);
        System.arraycopy(encryptedMessageBytes, 0, bytesToEncode, ivBytes.length, encryptedMessageBytes.length);

        return Base64.getEncoder().encodeToString(bytesToEncode);
    }

    public static String encrypt(String rawText, int ivLength, String transformation, String secretKey) throws
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchPaddingException, NoSuchAlgorithmException {
        return encrypt(rawText, ivLength, transformation, secretKeyFromBase64(secretKey));
    }

    public static String encrypt(String rawText, SecretKey secretKey) throws InvalidKeyException {
        try {
            return encrypt(rawText, DEFAULT_IV_LENGTH, DEFAULT_TRANSFORMATION, secretKey);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e); //TODO: maybe switch to IllegalStateException or a custom exception
        }
    }

    public static String encrypt(String rawText, String secretKey) throws InvalidKeyException {
        try {
            return encrypt(rawText, DEFAULT_IV_LENGTH, DEFAULT_TRANSFORMATION, secretKeyFromBase64(secretKey));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e); //TODO: maybe switch to IllegalStateException or a custom exception
        }
    }

    public static String decrypt(String encryptedText, int ivLength, String transformation, SecretKey secretKey) throws
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchPaddingException, NoSuchAlgorithmException {

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);

        byte[] ivBytes = new byte[ivLength];
        System.arraycopy(decodedBytes, 0, ivBytes, 0, ivLength);

        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));

        int messageDecryptedBytesLength = decodedBytes.length - ivLength;
        byte[] messageDecryptedBytes = new byte[messageDecryptedBytesLength];
        System.arraycopy(decodedBytes, ivLength, messageDecryptedBytes, 0, messageDecryptedBytesLength);

        return new String(cipher.doFinal(messageDecryptedBytes), StandardCharsets.UTF_8);
    }

    public static String decrypt(String encryptedText, int ivLength, String transformation, String secretKey) throws
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException,
            NoSuchPaddingException, NoSuchAlgorithmException {
        return decrypt(encryptedText, ivLength, transformation, secretKeyFromBase64(secretKey));
    }

    public static String decrypt(String encryptedText, SecretKey secretKey) throws InvalidKeyException {
        try {
            return decrypt(encryptedText, DEFAULT_IV_LENGTH, DEFAULT_TRANSFORMATION, secretKey);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e); //TODO: maybe switch to IllegalStateException or a custom exception
        }
    }

    public static String decrypt(String encryptedText, String secretKey) throws InvalidKeyException {
        try {
            return decrypt(encryptedText, DEFAULT_IV_LENGTH, DEFAULT_TRANSFORMATION, secretKeyFromBase64(secretKey));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e); //TODO: maybe switch to IllegalStateException or a custom exception
        }
    }

    public static SecretKey generateSecretKey(int passwordLength, int saltLength, int hashIterations) {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM);
            SecureRandom secureRandom = new SecureRandom();
            KeySpec keySpec = new PBEKeySpec(generateRandomPassword(passwordLength), secureRandom.generateSeed(saltLength), hashIterations, SECRET_KEY_LENGTH);
            return new SecretKeySpec(secretKeyFactory.generateSecret(keySpec).getEncoded(), "AES");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e); //TODO: maybe switch to IllegalStateException or a custom exception
        }
    }

    public static SecretKey generateSecretKey() {
        return generateSecretKey(1024, 32, 10000);
    }

}
