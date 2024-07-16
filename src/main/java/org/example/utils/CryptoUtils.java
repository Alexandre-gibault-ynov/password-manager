package org.example.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;

public class CryptoUtils {
    static{
        Security.addProvider(new BouncyCastleProvider());
    }

    private final static String ALGORITHM = "AES/GCM/NoPadding";
    private final static int KEY_SIZE = 256;
    private final static int TAG_SIZE = 128;
    private final static int IV_SIZE = 12;

    public static SecretKey generateSecretKey() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES", "BC");
        keyGen.init(KEY_SIZE, new SecureRandom());
        return keyGen.generateKey();
    }

    public static byte[] encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        byte[] iv = new byte[IV_SIZE];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        byte[] encryptedDataWithIV = new byte[encryptedData.length + IV_SIZE];
        System.arraycopy(iv, 0, encryptedDataWithIV, 0, IV_SIZE);
        System.arraycopy(encryptedData, 0, encryptedDataWithIV, IV_SIZE, encryptedData.length);
        return encryptedDataWithIV;
    }

    public static String decrypt(byte[] encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(encryptedData, 0, iv, 0, IV_SIZE);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_SIZE, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] originalData = cipher.doFinal(encryptedData, IV_SIZE, encryptedData.length - IV_SIZE);
        return new String(originalData);
    }

    public static void saveKey(SecretKey key, String filePath) throws Exception {
        byte[] encoded = key.getEncoded();
        Files.write(Paths.get(filePath), encoded);
    }

    public static SecretKey loadKey(String filePath) throws Exception {
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        return new SecretKeySpec(encoded, "AES");
    }
}
