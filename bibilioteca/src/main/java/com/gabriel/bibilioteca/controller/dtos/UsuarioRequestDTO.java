package com.gabriel.bibilioteca.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UsuarioRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
