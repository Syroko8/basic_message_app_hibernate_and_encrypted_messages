package com.example.utils;

import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {
    public String messageCrypted(String message, String userKey) {
        String messageCrp = "";
        try {
            // Decodificar la clave proporcionada por el usuario
            byte[] decodedKey = Base64.getDecoder().decode(userKey);
            SecretKey originalKey = new SecretKeySpec(decodedKey, "AES");

            // Configurar el cifrador en modo ENCRYPT_MODE
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.ENCRYPT_MODE, originalKey);

            // Cifrar el mensaje y codificar el resultado en Base64
            byte[] encryptedBytes = aesCipher.doFinal(message.getBytes("UTF-8"));
            messageCrp = Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageCrp;
    }

    public String messageDecrypted(String messageCrypted, String userKey) {
        String messageDcrp = "";
        try {
            // Decodificar la clave proporcionada por el usuario
            byte[] decodedKey = Base64.getDecoder().decode(userKey);
            SecretKey originalKey = new SecretKeySpec(decodedKey, "AES");

            // Configurar el cifrador en modo DECRYPT_MODE
            Cipher aesCipher = Cipher.getInstance("AES");
            aesCipher.init(Cipher.DECRYPT_MODE, originalKey);

            // Decodificar el mensaje cifrado desde Base64 y desencriptar
            byte[] encryptedBytes = Base64.getDecoder().decode(messageCrypted);
            byte[] decryptedBytes = aesCipher.doFinal(encryptedBytes);
            messageDcrp = new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageDcrp;
    }

    // Método para generar una clave AES válida en Base64
    public static String generateBase64Key() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // También puedes usar 192 o 256 bits
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}