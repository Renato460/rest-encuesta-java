package org.example.service;

import org.example.model.Encuesta;

import java.util.List;

public interface EncuestaService {
    List<Encuesta> findAllEncuestas();

    Encuesta guardarEncuesta(Encuesta encuesta);
}
