package com.gabriel.bibilioteca.controller.dtos;

import com.gabriel.bibilioteca.entities.Livro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String genero;
    private Integer anoPublicacao;
    private String isbn;
    private Integer quantidade;

    public LivroResponseDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.genero = livro.getGenero();
        this.anoPublicacao = livro.getAnoPublicacao();
        this.isbn = livro.getIsbn();
        this.quantidade = livro.getQuantidade();
    }
}
