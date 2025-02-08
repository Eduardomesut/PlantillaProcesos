/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.examen.ftp;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class Cliente {

    public static void main(String[] args) {
        System.out.println("Bienvenido al servicio de transferencia FTP");
        boolean salir = false;
        boolean conectado = false;
        GestorFTP gestor = new GestorFTP();
        String respuesta;
        Scanner sc = new Scanner(System.in);
        mostrarAyuda();
        do {
            //connect 10.0.2.15 marco/marco

            respuesta = sc.nextLine();
            String[] partes = respuesta.split(" ");
            try {
                if (respuesta.contains("connect") && partes.length == 3 && respuesta.contains("/")) {

                    String servidor = partes[1];
                    String usuario = partes[2].substring(0, partes[2].indexOf("/"));
                    String password = partes[2].substring(partes[2].indexOf("/") + 1, partes[2].length());

                    if (!conectado) {
                        if (gestor.conectar(servidor, 21, usuario, password)) {
                            System.out.println("Conexión establecida correctamente con " + servidor);
                            conectado = true;
                        } else {
                            System.out.println("Error en los datos de conexión!!");
                        }
                    } else {
                        System.out.println("Ya esta conectado a otro servidor, desconecte primero");
                    }
                } else if (respuesta.equals("List")) {
                    if (conectado) {
                        gestor.listarFicheros();
                    } else {
                        System.out.println("Necesitas conexión primero con un servidor!");
                    }

                } else if (respuesta.contains("changePath") && partes.length > 1) {
                    if (conectado) {
                        String path = partes[1];
                        if (gestor.cambiarDirectorio(path)) {
                            System.out.println("Directorio cambiado");

                        } else {
                            System.out.println("Lo siento, parece que el directorio no esta disponible");
                        }
                    } else {
                        System.out.println("Necesitas conexión primero con un servidor!");
                    }

                } else if (respuesta.contains("down") && partes.length > 1) {
                    String ficheroRemoto = partes[1];
                    String pathLocal = System.getProperty("user.dir");
                    if (conectado) {
                        if (gestor.descargarFichero(ficheroRemoto, pathLocal)) {
                            System.out.println("Fichero descargado correctamente!! Se ha guardado en la ruta: " + pathLocal);
                        } else {
                            System.out.println("Error en la descarga o lectura de fichero remoto!");
                        }
                    } else {
                        System.out.println("Necesitas conexión primero con un servidor!");
                    }

                } else if (respuesta.contains("up") && partes.length > 1) {
                    String ficheroLocal = partes[1];
                    if (conectado) {
                        if (gestor.subirFichero(ficheroLocal)) {
                            System.out.println("Fichero subido con exito al servidor!!");
                        } else {
                            System.out.println("Error en la subida del fichero al servidor!");
                        }
                    } else {
                        System.out.println("Necesitas conexión primero con un servidor!");
                    }

                } else if (respuesta.equals("Disconnect")) {
                    gestor.desconectar();
                    conectado = false;
                    System.out.println("Desconectado del servidor!");
                } else if (respuesta.equals("s")) {
                    System.out.println("Saliendo de la aplicación.....");
                    if (conectado) {
                        gestor.desconectar();
                        System.out.println("Desconectando servidor......");
                    }
                    salir = true;
                }  else {
                    System.out.println("Orden incorrecta, aqui tiene la ayuda de comandos disponibles");
                    mostrarAyuda();
                }

            }catch (SocketException ex){
                System.out.println("Error al conectar con el servidor FTP, pruebe de nuevo...");
                ex.printStackTrace();
            }catch (UnknownHostException ex){
                System.out.println("Error al conectar con el servidor FTP, host desconocido...");
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (!salir);

    }

    public static void mostrarAyuda() {
        System.out.println("AYUDA");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("-Para conectarse a un servidor remoto, comando: connect NombreServidor Usuario/Contraseña");
        System.out.println("-Para mostrar contenido del directorio de trabajo, comando: List");
        System.out.println("-Para cambiar el directorio de trabajo, comando: changePath NuevoPAth");
        System.out.println("-Para descargar fichero delde el servidor a local, comando: down NombreFicheroRemoto");
        System.out.println("-Para subir fichero desde local al servidor, comando: up NombreFicheroLocal");
        System.out.println("-Para desconectar de un servidor remoto, comando: Disconnect");
        System.out.println("-Para salir de la aplicación, comando: s");
        System.out.println("-------------------------------------------------------------------------");

    }
}
