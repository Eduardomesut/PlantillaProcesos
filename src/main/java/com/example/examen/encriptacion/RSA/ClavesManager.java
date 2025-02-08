/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.examen.encriptacion.RSA;

/**
 *
 * @author Usuario
 */
// ClavesManager.java
import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class ClavesManager {

    private static final String FICHERO_CLAVE_PUBLICA = "clavePublica.key";
    private static final String FICHERO_CLAVE_PRIVADA = "clavePrivada.key";

    public static KeyPair generarClaves() throws Exception {
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(2048);
        return generador.generateKeyPair();
    }

    public static void guardarClaves(KeyPair claves) throws Exception {
        Files.write(new File(FICHERO_CLAVE_PUBLICA).toPath(), claves.getPublic().getEncoded());
        Files.write(new File(FICHERO_CLAVE_PRIVADA).toPath(), claves.getPrivate().getEncoded());
    }

    public static PublicKey getClavePublica() throws Exception {
        File ficheroClavePublica = new File(FICHERO_CLAVE_PUBLICA);
        byte[] bytesClavePublica = Files.readAllBytes(ficheroClavePublica.toPath());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytesClavePublica);
        return keyFactory.generatePublic(publicKeySpec);
    }

    public static PrivateKey getClavePrivada() throws Exception {
        File ficheroClavePrivada = new File(FICHERO_CLAVE_PRIVADA);
        byte[] bytesClavePrivada = Files.readAllBytes(ficheroClavePrivada.toPath());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytesClavePrivada);
        return keyFactory.generatePrivate(privateKeySpec);
    }

    public static void main(String[] args) {
        try {
            KeyPair claves = generarClaves();
            guardarClaves(claves);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




