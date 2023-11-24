package rs.payment.exchange.dto.security;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class HashingUtil {

    private HashingUtil() {
    }

    private static final int KEY_LENGTH = 256;
    private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA256";

    private static final String HS256 = "HmacSHA256";

    public static String hmacSha256ToBase64(SecretKey secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public static SecretKey hmacSha256FromBase64(String base64Encoded) {
        return new SecretKeySpec(Base64.getDecoder().decode(base64Encoded), HS256);
    }

    public static SecretKey generateHmac256SigningKey(byte[] key, byte[] message) {
        try {
            Mac hmacSha256 = Mac.getInstance(HS256);
            SecretKeySpec secretKey = new SecretKeySpec(key, HS256);

            hmacSha256.init(secretKey);
            byte[] signature = hmacSha256.doFinal(message);

            //return Base64.getEncoder().encodeToString(signature);
            return new SecretKeySpec(signature, HS256);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e); //TODO: maybe switch to IllegalStateException or a custom exception;
        }
    }

    public static SecretKey generateHmac256SigningKey(String key, String message) {
        return generateHmac256SigningKey(key.getBytes(StandardCharsets.UTF_8), message.getBytes(StandardCharsets.UTF_8));
    }

    public static SecretKey generateHmac256SigningKey() {
        SecureRandom sr = new SecureRandom();

        byte[] key = new byte[32];
        byte[] message = new byte[1024];
        sr.nextBytes(key);
        sr.nextBytes(message);

        return generateHmac256SigningKey(key, message);
    }

    public static byte[] calculateHash(String strToHash, int iterations, byte[] salt) {
        try {
            char[] chars = Base64.getEncoder().encodeToString(strToHash.getBytes(StandardCharsets.UTF_8)).toCharArray();
            PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(HASH_ALGORITHM);

            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e); //TODO: maybe switch to IllegalStateException or a custom exception;
        }
    }

    public static String calculateHash(String strToHash, int iterations, String salt) {
        byte[] hash = calculateHash(strToHash, iterations, Base64.getDecoder().decode(salt));
        return Base64.getEncoder().encodeToString(hash);
    }

    public static CryptData calculateHash(String strToHash) {
        SecureRandom sr = new SecureRandom();
        int iterations = sr.nextInt(7168) + 1024;
        byte[] salt = new byte[KEY_LENGTH / 8];
        sr.nextBytes(salt);

        byte[] hash = calculateHash(strToHash, iterations, salt);

        CryptData cryptData = new CryptData();
        cryptData.setIteration(iterations);
        cryptData.setSalt(Base64.getEncoder().encodeToString(salt));
        cryptData.setHash(Base64.getEncoder().encodeToString(hash));

        return cryptData;
    }

    public static boolean checkPass(String password, String salt, int iterations, String hash) {
        return hash.equals(calculateHash(password, iterations, salt));
    }

}
