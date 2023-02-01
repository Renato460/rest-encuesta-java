package org.example.repository;

import org.example.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GeneroRepository extends JpaRepository<Genero,Integer> {
}
