package it.epicode.s6d1.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Random;


@Entity
@Data
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String categoria;
    private String titolo;
    private  String cover;
    private String contenuto;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;

    private int tempoDiLettura;
}
