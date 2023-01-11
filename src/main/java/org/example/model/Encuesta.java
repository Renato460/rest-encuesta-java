package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Table(name = "MAIL")@DynamicInsert@DynamicUpdate
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "MAIL_ID",
            nullable = false,
            updatable = false)
    private Integer id;

    @Column(name = "MAIL",
            nullable = false,
            columnDefinition = "varchar(255) default ''",
            unique = true
    )
    private String mail;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "GENERO_ID", referencedColumnName = "GENERO_ID")
    private Genero genero;

}