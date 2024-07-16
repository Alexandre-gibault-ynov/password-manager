package org.example.controller;

import org.example.model.Credential;
import org.example.utils.CryptoUtils;
import org.example.view.ConsultationScreen;
import org.example.view.MainFrame;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CredentialController {
    private static final String FILE_PATH = "credentials.enc";
    private static final String KEY_PATH = "secret.key";
    private final MainFrame mainFrame;
    private List<Credential> credentials;
    private SecretKey secretKey;

    public CredentialController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.credentials = new ArrayList<>();

        try {
            if (Files.exists(Paths.get(KEY_PATH))) {
                this.secretKey = CryptoUtils.loadKey(KEY_PATH);
            } else {
                this.secretKey = CryptoUtils.generateSecretKey();
                CryptoUtils.saveKey(secretKey, KEY_PATH);
            }
            loadCredentials();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCredential(String name, String identifier, String password, String authService) {
        Credential credential = new Credential(name, identifier, password, authService);
        credentials.add(credential);
        saveCredentials();
        mainFrame.switchPanel(new ConsultationScreen(this.mainFrame, this));
    }

    public void updateCredential(int index, String name, String identifier, String password, String authService) {
        if (index >= 0 && index < credentials.size()) {
            Credential credential = credentials.get(index);
            credential.setName(name);
            credential.setIdentifier(identifier);
            credential.setPassword(password);
            credential.setAuthService(authService);
            saveCredentials();
            mainFrame.switchPanel(new ConsultationScreen(this.mainFrame, this));
        }
    }

    public void removeCredential(int index) {
        if (index >= 0 && index < credentials.size()) {
            credentials.remove(index);
            saveCredentials();
            mainFrame.switchPanel(new ConsultationScreen(this.mainFrame, this));
        }
    }

    private void saveCredentials() {
        try {
            StringBuilder data = new StringBuilder();
            for (Credential credential : credentials) {
                data.append(credential.getName())
                        .append(",")
                        .append(credential.getIdentifier())
                        .append(",")
                        .append(credential.getPassword())
                        .append(",")
                        .append(credential.getAuthService())
                        .append("\n");
            }
            byte[] encryptedData = CryptoUtils.encrypt(data.toString(), secretKey);
            Files.write(Paths.get(FILE_PATH), encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCredentials() {
        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                byte[] encryptedData = Files.readAllBytes(Paths.get(FILE_PATH));
                String decryptedData = CryptoUtils.decrypt(encryptedData, secretKey);
                credentials = decryptedData.lines()
                        .map(line -> {
                            String[] parts = line.split(",");
                            return new Credential(parts[0], parts[1], parts[2], parts[3]);
                        })
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Generate a password with the given length. If the length is greater or equal to 4
     * the generator creates a password with certain rules. Otherwise, it generates a simple
     * password with the given length.
     *
     * @param length The number of characters for the password
     * @return Random password
     */
    public String generatePassword(int length) {
        PasswordGenerator generator = new PasswordGenerator();
        CharacterRule lowerCaseRule = new CharacterRule(EnglishCharacterData.LowerCase, 1);
        CharacterRule upperCaseRule = new CharacterRule(EnglishCharacterData.UpperCase, 1);
        CharacterRule digitRule = new CharacterRule(EnglishCharacterData.Digit, 1);
        CharacterRule specialCharRule = new CharacterRule(EnglishCharacterData.Special, 1);

        return length >= 4 ? generator.generatePassword(length, Arrays.asList(lowerCaseRule, upperCaseRule, digitRule, specialCharRule)) : generator.generatePassword(length);
    }

    public List<Credential> getCredentials() {
        return credentials;
    }
}
