package it.epicode.s6d1.model;


import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalTime;

@Data
public class BlogRequest {

    @NotNull(message = "categoria obbligatorio")
    @NotBlank(message = "Non puoi inserire spazi vuoti senza inserire lettere")
    @NotEmpty(message = "campo vuoto")
    private String categoria;

    @NotNull(message = "titolo obbligatorio")
    @NotBlank(message = "Non puoi inserire spazi vuoti senza inserire lettere")
    @NotEmpty(message = "campo vuoto")
    private String titolo;

    @NotNull(message = "cover obbligatorio")
    @NotBlank(message = "Non puoi inserire spazi vuoti senza inserire un url")
    @NotEmpty(message = "campo vuoto")
    private  String cover;

    @NotNull(message = "contenuto obbligatorio")
    @NotBlank(message = "Non puoi inserire spazi vuoti senza inserire lettere")
    @NotEmpty(message = "campo vuoto")
    private String contenuto;

    @NotNull(message = "autore obbligatorio")
    private Integer idAutore;

    @NotNull(message = "Tempo lettura obbligatorio")
    private int tempoDiLettura;
}
