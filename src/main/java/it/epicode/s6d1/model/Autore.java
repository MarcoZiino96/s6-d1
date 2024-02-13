package it.epicode.s6d1.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Random;


@Data
public class Autore {

    private int id = new Random().nextInt(1, Integer.MAX_VALUE);
    private String nome;
    private String cognome;

    private String email;
    private LocalDate dataDiNascita;
    private String avatar;

    public Autore(String nome, String cognome, String email, LocalDate dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        if (nome != null || cognome != null) {
            this.avatar = "http://ui-avatars.com/api/?name=" + nome +"+"+ cognome;
        };
    }
}
