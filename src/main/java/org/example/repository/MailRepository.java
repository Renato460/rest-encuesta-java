package org.example.repository;

import org.example.model.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface MailRepository extends JpaRepository<Encuesta,Integer> {
    Optional<Encuesta> findByMail(String email);
}
