package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.Genero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EncuestaDTO {

    private Integer id;


    private String mail;


    private Genero genero;

}