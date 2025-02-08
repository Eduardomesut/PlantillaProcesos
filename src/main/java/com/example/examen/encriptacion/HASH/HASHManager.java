/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.examen.encriptacion.HASH;

/**
 *
 * @author Usuario
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HASHManager {
    private static final String ALGORITMO = "SHA-256";

    public static byte[] getDigest(byte[] mensaje) throws NoSuchAlgorithmException {
        byte[] resumen = null;
        try {
            MessageDigest algoritmo = MessageDigest.getInstance(ALGORITMO);
            algoritmo.reset();
            algoritmo.update(mensaje);
            resumen = algoritmo.digest();
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
        return resumen;
    }

    public static boolean compararResumenes(byte[] resumen1, byte[] resumen2) throws NoSuchAlgorithmException {
        boolean sonIguales;
        try {
            MessageDigest algoritmo = MessageDigest.getInstance(ALGORITMO);

            algoritmo.reset();
            sonIguales = algoritmo.isEqual(resumen1, resumen2);
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
        return sonIguales;
    }
}