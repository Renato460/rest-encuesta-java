package org.example.repository;

import org.example.model.Encuesta;
import org.example.model.Genero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest

class GeneroRepositoryTest {
    @Autowired
    GeneroRepository generoRepository;

    @MockBean
    private MailRepository mailRepository;

    private Genero genero;

    @BeforeEach
    void setUp(){
        this.genero = new Genero();
        genero.setGeneroId(3);
        genero.setGeneroName("Clasica");

        Encuesta encuesta = new Encuesta();
        encuesta.setId(1);
        encuesta.setMail("prueba@prueba.com");
        encuesta.setGenero(genero);
    }

    @Test
    void findAllGenerosTest() {

        generoRepository.save(genero);

        List<Genero> generos = generoRepository.findAll();
        Assertions.assertFalse(generos.isEmpty());
    }
}
