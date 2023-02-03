package org.example.service.impl;

import org.example.http_errors.ConflictException;
import org.example.http_errors.NotFoundException;
import org.example.model.Encuesta;
import org.example.model.Genero;
import org.example.model.dto.EncuestaDTO;
import org.example.repository.GeneroRepository;
import org.example.repository.MailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EncuestaServiceImplTest {

    @Mock
    private MailRepository mailRepository;

    @Mock
    private GeneroRepository generoRepository;

    @InjectMocks
    EncuestaServiceImpl encuestaService;

    private List<Genero> generoList;

    private List<Encuesta> encuestaList;

    private Encuesta encuesta;

    @BeforeEach
    void setUp(){
        Genero genero = new Genero();
        genero.setGeneroId(1);
        genero.setGeneroName("Jaz");

        this.generoList = new ArrayList<>();
        generoList.add(genero);

        encuesta = new Encuesta();
        encuesta.setId(1);
        encuesta.setMail("prueba@prueba.com");
        encuesta.setGenero(genero);

        encuestaList = new ArrayList<>();
        this.encuestaList.add(encuesta);
    }

    @Test
    void findAllGenerosTest(){
        when(generoRepository.findAll()).thenReturn(generoList);
        List<Genero> generoList1 = encuestaService.findAllGeneros();
        Assertions.assertFalse(generoList1.isEmpty());
    }

    @Test
    void findAllGenerosTestException() {
        when(generoRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThrows(NotFoundException.class,()->encuestaService.findAllGeneros());
    }

    @Test
    void findAllEncuestasTest(){
        when(mailRepository.findAll()).thenReturn(encuestaList);
        List<Encuesta> encuestaList1 = encuestaService.findAllEncuestas();
        Assertions.assertFalse(encuestaList1.isEmpty());
    }

    @Test
    void findAllEncuestasTestException() {
        when(mailRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThrows(NotFoundException.class,()->encuestaService.findAllEncuestas());
    }

    @Test
    void guardarEncuestaTest(){
        EncuestaDTO encuestaDTO = new EncuestaDTO(encuesta.getId(), "prueba2@prueba.cl", encuesta.getGenero());

        when(mailRepository.findByMail("prueba2@prueba.cl"))
                .thenReturn(Optional.empty());
        Encuesta encuesta1 = encuestaService.guardarEncuesta(encuestaDTO);
        Assertions.assertNotNull(encuesta1);
    }

    @Test
    void guardarEncuestaConflictoTest(){
        EncuestaDTO encuestaDTO = new EncuestaDTO(encuesta.getId(), encuesta.getMail(), encuesta.getGenero());

        when(mailRepository.findByMail(encuesta.getMail()))
                .thenReturn(Optional.of(encuesta));
        Assertions.assertThrows(ConflictException.class,()->encuestaService.guardarEncuesta(encuestaDTO));
    }

}
