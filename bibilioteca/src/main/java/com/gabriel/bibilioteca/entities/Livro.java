package com.gabriel.bibilioteca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tb_livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private Integer anoPublicacao;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private Integer quantidade;

    public Livro() {
    }

    public Livro(String titulo, String autor, String genero, Integer anoPublicacao, String isbn, Integer quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anoPublicacao = anoPublicacao;
        this.isbn = isbn;
        this.quantidade = quantidade;
    }
}
