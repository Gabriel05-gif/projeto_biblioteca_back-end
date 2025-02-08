package com.gabriel.bibilioteca.controller.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroRequestDTO {


    @NotNull
    private String titulo;

    @NotNull
    private String autor;

    @NotNull
    private String genero;

    @NotNull
    private Integer anoPublicacao;

    @NotNull
    private String isbn;

    @NotNull
    private Integer quantidade;
}
