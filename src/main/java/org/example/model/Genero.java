package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Table(name = "GENERO")@DynamicInsert@DynamicUpdate
public class Genero {

    @Id
    @Column(name = "GENERO_ID",
            nullable = false,
            updatable = false)
    private Integer genero_id;

    @Column(name = "GENERO",
            nullable = false,
            columnDefinition = "varchar(255) default ''",
            unique = true
    )
    private String genero;

}