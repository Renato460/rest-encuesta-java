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
            genero.setGenero_id(1);
            genero.setGenero("Jaz");

            Genero genero2 = new Genero();
            genero2.setGenero_id(2);
            genero2.setGenero("Rock");

            Encuesta encuesta = new Encuesta();
            encuesta.setId(1);
            encuesta.setMail("mail@mail.com");
            encuesta.setGenero(genero);

            Encuesta encuesta2 = new Encuesta();
            encuesta2.setId(2);
            encuesta2.setMail("mail@mail2.com");
            encuesta2.setGenero(genero2);

            mailRepository.save(encuesta);

            mailRepository.save(encuesta2);
        };
    }
}
