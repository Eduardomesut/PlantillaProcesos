/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.examen.encriptacion.RSA;

/**
 *
 * @author Usuario
 */

// RSAReceptorManager.java
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.Key;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class RSAReceptorManager {

    private static byte[] descifrar(byte[] mensajeCifrado, Key clave) throws Exception {
        Cipher descifrado = Cipher.getInstance("RSA");
        descifrado.init(Cipher.DECRYPT_MODE, clave);
        return descifrado.doFinal(mensajeCifrado);
    }

    public static void main(String[] args) {
        PublicKey clavePublica;
        File fichero = new File("datosCifrados.rsa");
        try {
            clavePublica = ClavesManager.getClavePublica();
            byte[] mensajeCifrado = Files.readAllBytes(fichero.toPath());
            byte[] mensajeDescifrado = descifrar(mensajeCifrado, clavePublica);
            System.out.println(new String(mensajeDescifrado, StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}