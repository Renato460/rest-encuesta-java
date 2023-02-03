package org.example;

import org.example.model.Encuesta;
import org.example.model.Genero;
import org.example.repository.MailRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestEncuestaJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestEncuestaJavaApplication.class, args);
    }
    @Bean
    public CommandLineRunner init(MailRepository mailRepository) {
        return args -> {

            Genero genero = new Genero();
            genero.setGeneroId(1);
            genero.setGeneroName("Jaz");

            Genero genero2 = new Genero();
            genero2.setGeneroId(2);
            genero2.setGeneroName("Rock");

            Genero genero3 = new Genero();
            genero3.setGeneroId(3);
            genero3.setGeneroName("Blues");

            Encuesta encuesta = new Encuesta();
            encuesta.setId(1);
            encuesta.setMail("mail@mail.com");
            encuesta.setGenero(genero);

            Encuesta encuesta2 = new Encuesta();
            encuesta2.setId(2);
            encuesta2.setMail("mail@mail2.com");
            encuesta2.setGenero(genero2);

            Encuesta encuesta3 = new Encuesta();
            encuesta3.setId(3);
            encuesta3.setMail("mail3@mail3.com");
            encuesta3.setGenero(genero3);

            mailRepository.save(encuesta);

            mailRepository.save(encuesta2);

            mailRepository.save(encuesta3);
        };
    }
}
