/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.examen.encriptacion.AES;

/**
 *
 * @author Usuario
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.Key;

public class DescifradorAESsimple {
    public static void main(String[] args) {
        final int LONGITUD_BLOQUE = 16; // Expresado en bytes
        final String NOMBRE_FICHERO = "mensaje_cifrado.txt";
        final String PASSWORD = "MeLlamoSpiderman";

        try {
            AESSimpleManager aes = new AESSimpleManager();
            Key clave = aes.obtenerClave(PASSWORD, LONGITUD_BLOQUE);

            BufferedReader br = new BufferedReader(new FileReader(new File(NOMBRE_FICHERO)));
            String textoCifrado = br.readLine();
            br.close();

            String textoDescifrado = aes.descifrar(textoCifrado, clave);
            System.out.println("El mensaje descifrado es: " + textoDescifrado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
