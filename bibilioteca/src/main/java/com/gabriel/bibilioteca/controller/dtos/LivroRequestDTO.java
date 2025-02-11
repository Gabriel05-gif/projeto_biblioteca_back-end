package com.gabriel.bibilioteca.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroRequestDTO {


    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @NotBlank
    private String genero;

    @NotBlank
    private Integer anoPublicacao;

    @NotBlank
    private String isbn;

    @NotBlank
    private Integer quantidade;
}
