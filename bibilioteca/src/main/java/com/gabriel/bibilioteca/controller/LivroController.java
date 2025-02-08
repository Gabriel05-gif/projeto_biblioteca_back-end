package com.gabriel.bibilioteca.controller;

import com.gabriel.bibilioteca.business.services.LivroService;
import com.gabriel.bibilioteca.controller.dtos.LivroRequestDTO;
import com.gabriel.bibilioteca.controller.dtos.LivroResponseDTO;
import com.gabriel.bibilioteca.entities.Livro;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<LivroResponseDTO> cadastrarLivro(@RequestBody @Valid LivroRequestDTO livroRequestDTO) {
        LivroResponseDTO livroCadastrado = livroService.cadastrarLivro(livroRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCadastrado);
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<List<LivroResponseDTO>> buscarLivro(@RequestParam(required = false) String titulo,
                                                   @RequestParam(required = false) String autor,
                                                   @RequestParam(required = false) String genero,
                                                   @RequestParam(required = false) String isbn) {
        List<LivroResponseDTO> livrosResponseDTO = livroService.buscarLivros(titulo, autor, genero, isbn);
        return ResponseEntity.status(HttpStatus.OK).body(livrosResponseDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable Long id) {
        livroService.deletarLivro(id);
        return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizarLivro(@PathVariable Long id, @RequestBody @Valid LivroRequestDTO livroRequestDTO) {
        LivroResponseDTO livroResponseDTO = livroService.atualizarLivro(id, livroRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(livroResponseDTO);
    }
}
