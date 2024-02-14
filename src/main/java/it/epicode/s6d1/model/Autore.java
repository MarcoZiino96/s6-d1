package it.epicode.s6d1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;


@Entity
@Data
@NoArgsConstructor
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;

    @JsonIgnore
    @OneToMany(mappedBy = "autore")
    private List<Blog> blogs;



    public Autore(String nome, String cognome, String email, LocalDate dataDiNascita){
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        if (nome != null && cognome != null) {
            this.avatar = "http://ui-avatars.com/api/?name=" + nome +"+"+ cognome;
        };
    }
}
