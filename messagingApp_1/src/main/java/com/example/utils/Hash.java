package com.example.utils;

import java.security.MessageDigest;

/**
 * @author Nicolás Puebla Martín
 * 
 * Clase cuyo método genera una huella hash de un string. 
 */
public class Hash {

    // Atributos de la clase.
    private MessageDigest md;

    public Hash(){
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String hashPasswd(String passwd){
        // Obtenemos los bytes de la contraseña.
        byte[] passwdBytes = passwd.getBytes();
        // Introducimos la contraseña en el objeto MessajeDigest.
        md.update(passwdBytes);
        // Obtenemos la huella.
        byte[] hashedPasswd = md.digest();
        // Devolvemos la huella.
        return new String(hashedPasswd);
    }
}
