/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.examen.rest.controllers;

import com.example.examen.rest.repositories.VotoRepository;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Eduardo
 */

//Se puede intentar hacer con objetos
@RestController
@RequestMapping("/api")
public class VotacionesController {
    private final VotoRepository votacionesData;

    public VotacionesController(VotoRepository votacionesData) {
        this.votacionesData = votacionesData;
    }

    @GetMapping("/voto/{name}")
    public ResponseEntity<String> createVote(@PathVariable String name){

        String resultado = name + ", votos: " + votacionesData.addVoto(name);
        return ResponseEntity.ok(resultado);
    }
    @GetMapping("/votos")
    public ResponseEntity<HashMap<String, Integer>> getVotes(){
        HashMap<String, Integer> resultado = votacionesData.getVotos();
        return ResponseEntity.ok(resultado);

    }


}
