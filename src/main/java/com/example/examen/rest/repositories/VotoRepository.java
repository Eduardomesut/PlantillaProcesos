/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.examen.rest.repositories;

import java.util.HashMap;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eduardo
 */
@Repository
public class VotoRepository {
    private HashMap<String, Integer> votaciones = new HashMap<>();
    public synchronized int addVoto(String voto){
        if (votaciones.containsKey(voto)){
            votaciones.put(voto, votaciones.get(voto)+1);

        }else {
            votaciones.put(voto, 1);
        }
        return votaciones.get(voto);
    }
    public synchronized HashMap<String, Integer> getVotos(){

        return this.votaciones;
    }
}
