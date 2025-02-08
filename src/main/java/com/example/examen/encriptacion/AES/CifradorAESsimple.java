/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.examen.encriptacion.AES;

/**
 *
 * @author Usuario
 */

import java.io.PrintWriter;
import java.security.Key;

public class CifradorAESsimple {
    public static void main(String[] args) {
        final int LONGITUD_BLOQUE = 16; // Expresado en bytes
        final String NOMBRE_FICHERO = "mensaje_cifrado.txt";
        final String PASSWORD = "MeLlamoSpiderman";
        final String TEXTO_EN_CLARO = "La clave secreta de la caja fuerte es 3842873110";

        try {
            AESSimpleManager aes = new AESSimpleManager();
            Key clave = aes.obtenerClave(PASSWORD, LONGITUD_BLOQUE);
            String textoCifrado = aes.cifrar(TEXTO_EN_CLARO, clave);

            PrintWriter pw = new PrintWriter(NOMBRE_FICHERO);
            pw.write(textoCifrado);
            pw.close();
            System.out.println("El mensaje se ha cifrado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
