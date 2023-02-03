package org.example.service;

import org.example.model.Encuesta;
import org.example.model.Genero;
import org.example.model.dto.EncuestaDTO;

import java.util.List;

public interface EncuestaService {
    List<Encuesta> findAllEncuestas();
    List<Genero> findAllGeneros();
    Encuesta guardarEncuesta(EncuestaDTO encuesta);
}
