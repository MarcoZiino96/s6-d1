package it.epicode.s6d1.model;


import lombok.*;

import java.time.LocalTime;
import java.util.Random;

@Data
public class Blog {

    private int id = new Random().nextInt(1, Integer.MAX_VALUE);
    private String categoria;
    private String titolo;
    private  String cover;
    private String contenuto;
    private Autore autore;
    private LocalTime tempoDiLettura;
}
