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

class MailRepositoryTest {
    @Autowired
    MailRepository mailRepository;

    private Encuesta encuesta;

    @BeforeEach
    void setUp(){
        Genero genero = new Genero();
        genero.setGeneroId(3);
        genero.setGeneroName("Clasica");

        encuesta = new Encuesta();
        encuesta.setId(1);
        encuesta.setMail("prueba@prueba.com");
        encuesta.setGenero(genero);

    }

    @Test
    void guardarEncuestasTest() {

        Encuesta encuesta1 = mailRepository.save(encuesta);

        Assertions.assertNotNull(encuesta1);
    }

    @Test
    void findAllEncuestas(){
        mailRepository.save(encuesta);

        List<Encuesta> respuesta = mailRepository.findAll();

        Assertions.assertFalse(respuesta.isEmpty());
    }
}
