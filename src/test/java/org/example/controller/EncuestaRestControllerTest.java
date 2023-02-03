package org.example.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Encuesta;
import org.example.model.Genero;
import org.example.repository.MailRepository;
import org.example.service.EncuestaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@Import(EncuestaRestController.class)
class EncuestaRestControllerTest {
    @MockBean
    private EncuestaService encuestaService;

    @MockBean
    private MailRepository mailRepository;

    @Autowired
    private MockMvc mockMvc;

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
    void obtenerTodosLosGenerosTest() throws Exception {

        when(encuestaService.findAllGeneros()).thenReturn(generoList);

        mockMvc.perform(get("/api/v1/encuesta/generos/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.respuesta[0].generoId", is(1)))
                .andExpect(jsonPath("$.respuesta[0].generoName",is("Jaz")));


    }

    @Test
    void obtenerTodasLasEncuestasTest() throws Exception {

        when(encuestaService.findAllEncuestas()).thenReturn(encuestaList);

        mockMvc.perform(get("/api/v1/encuesta/all/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.respuesta[0].id", is(1)))
                .andExpect(jsonPath("$.respuesta[0].mail",is("prueba@prueba.com")))
                .andExpect(jsonPath("$.respuesta[0].genero.generoId",is(1)))
                .andExpect(jsonPath("$.respuesta[0].genero.generoName",is("Jaz")));;
    }
    @Test
    void guardarEncuestaTest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        when(encuestaService.guardarEncuesta(any())).thenReturn(encuesta);

        mockMvc.perform(post("/api/v1/encuesta/save")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(encuesta)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.respuesta.id").value(1))
                .andExpect(jsonPath("$.respuesta.mail").value("prueba@prueba.com"))
                .andExpect(jsonPath("$.respuesta.genero.generoId").value(1))
                .andExpect(jsonPath("$.respuesta.genero.generoName").value("Jaz"));


    }
}
