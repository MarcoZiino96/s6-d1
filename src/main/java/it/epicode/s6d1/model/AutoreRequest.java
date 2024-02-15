package it.epicode.s6d1.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;


@Data
public class AutoreRequest {

    @NotNull(message = "nome obbligatorio")
    @NotBlank(message = "Non puoi inserire spazi vuoti senza inserire lettere")
    @NotEmpty(message = "campo vuoto")
    private String nome;

    @NotNull(message = "cognome obbligatorio")
    @NotBlank(message = "Non puoi inserire spazi vuoti senza inserire lettere")
    @NotEmpty(message = "campo vuoto")
    private String cognome;

    @Email(message = "Inserire email valida")
    @NotEmpty(message = "Campo Vuoto")
    private String email;

    @NotNull(message = "Data nascita obbligatoria")
    private LocalDate dataDiNascita;

    @NotNull(message = "avatar obbligatorio")
    @NotEmpty(message = "campo vuoto")
    private String avatar;
}
