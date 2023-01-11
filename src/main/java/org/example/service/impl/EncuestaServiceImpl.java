package org.example.service.impl;

import org.example.http_errors.ConflictException;
import org.example.http_errors.NotFoundException;
import org.example.model.Encuesta;
import org.example.repository.MailRepository;
import org.example.service.EncuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Qualifier(value = "encuestaService")
public class EncuestaServiceImpl implements EncuestaService {
    private static final String SIN_RESULTADOS = "Sin Resultados";
    public static final String YA_EXISTE_MAIL = "Ya existe encuesta con éste mail: ";
    private final MailRepository mailRepository;

    @Autowired
    EncuestaServiceImpl(final MailRepository mailRepository){
        this.mailRepository = mailRepository;
    }

    @Transactional
    @Override
    public List<Encuesta> findAllEncuestas() {
        List<Encuesta> encuestas = mailRepository.findAll();
        if (encuestas.isEmpty()){
            throw new NotFoundException(SIN_RESULTADOS);
        }
        return encuestas;
    }

    @Transactional
    @Override
    public Encuesta guardarEncuesta(Encuesta encuesta) {
        mailRepository.findByMail(encuesta.getMail())
                .ifPresentOrElse(
                        p -> {
                            throw new ConflictException(YA_EXISTE_MAIL + p.getMail());
                        },
                        () ->  mailRepository.save(encuesta)
                );

        return encuesta;
    }
}
