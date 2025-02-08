/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.examen.encriptacion.RSA;

/**
 *
 * @author Usuario
 */

// RSAEmisorManager.java
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.Key;
import java.security.PrivateKey;
import javax.crypto.Cipher;

public class RSAEmisorManager {

    private static final String DATOS = "El código de acceso es 1513.";

    private static byte[] cifrar(String mensaje, Key clave) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, clave);
        return encryptCipher.doFinal(mensaje.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        PrivateKey clavePrivada;
        File fichero = new File("datosCifrados.rsa");
        try {
            clavePrivada = ClavesManager.getClavePrivada();
            byte[] mensajeCifrado = cifrar(DATOS, clavePrivada);
            Files.write(fichero.toPath(), mensajeCifrado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}