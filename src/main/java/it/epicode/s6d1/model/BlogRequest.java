package it.epicode.s6d1.model;


import lombok.*;

import java.time.LocalTime;

@Data
public class BlogRequest {

    private String categoria;
    private String titolo;
    private  String cover;
    private String contenuto;
    private int  idAutore;
    private int tempoDiLettura;
}
