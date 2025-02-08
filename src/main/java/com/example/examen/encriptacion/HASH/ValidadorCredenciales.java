/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.examen.encriptacion.HASH;

/**
 *
 * @author Usuario
 */

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;

public class ValidadorCredenciales {
    private static final String ENCODING_TYPE = "UTF-8";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce identificador (email): ");
        String identificador = sc.nextLine();
        System.out.print("Introduce contraseña: ");
        String password = sc.nextLine();

        try {
            byte[] resumen = HASHManager.getDigest(password.getBytes(ENCODING_TYPE));
            byte[] resumenAlmacenado = Files.readAllBytes(new File(identificador + ".credencial").toPath());

            if (HASHManager.compararResumenes(resumen, resumenAlmacenado)) {
                System.out.println("Autorizado");
            } else {
                System.out.println("Error de validación.");
                
                mostrarResumenHexadecimal(resumen);
                mostrarResumenHexadecimal(resumenAlmacenado);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sc.close();
    }

    private static void mostrarResumenHexadecimal(byte[] resumen) {
        String resumenHexadecimal = String.format("%064x", new BigInteger(1, resumen));
        System.out.println(resumenHexadecimal);
    }
}



