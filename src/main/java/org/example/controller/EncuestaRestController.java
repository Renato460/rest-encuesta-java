package org.example.controller;


import org.example.model.Encuesta;
import org.example.service.EncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EncuestaRestController {

    private final EncuestaService encuestaService;

    @Autowired
    public EncuestaRestController(final EncuestaService encuestaService){
        this.encuestaService = encuestaService;
    }

    @GetMapping(path = {"/encuesta/all"})
    public ResponseEntity<Map<String,Object>> obtenerTodasEncuestas(){
        List<Encuesta> encuestas = encuestaService.findAllEncuestas();
        Map<String, Object> respuesta = getStringObjectMap(encuestas);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PostMapping(path = "/encuesta/save")
    public ResponseEntity<Map<String,Object>> guardarEncuesta(
            @RequestBody Encuesta encuestaRequest
    ){
        Encuesta encuesta = encuestaService.guardarEncuesta(encuestaRequest);
        Map<String, Object> respuesta = getStringObjectMap(encuesta);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    private static Map<String, Object> getStringObjectMap(Object object) {
        Map<String,Object> respuesta = new HashMap<>();
        respuesta.put("respuesta", object);
        return respuesta;
    }
}