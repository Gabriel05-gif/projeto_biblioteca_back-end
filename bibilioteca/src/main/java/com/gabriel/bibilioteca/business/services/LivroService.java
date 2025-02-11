package com.gabriel.bibilioteca.business.services;

import com.gabriel.bibilioteca.controller.dtos.LivroRequestDTO;
import com.gabriel.bibilioteca.controller.dtos.LivroResponseDTO;
import com.gabriel.bibilioteca.entities.Livro;
import com.gabriel.bibilioteca.infrastructure.exceptions.DuplicacaoException;
import com.gabriel.bibilioteca.infrastructure.exceptions.LivroNaoEncontradoException;
import com.gabriel.bibilioteca.infrastructure.repositories.LivroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroResponseDTO cadastrarLivro(LivroRequestDTO livroRequestDTO) {
        log.info("Iniciando processo de cadastro de livro");
        if (livroRepository.findByIsbn(livroRequestDTO.getIsbn()).isPresent()) {
            log.error("Duplicação de livro. ISBN {}",livroRequestDTO.getIsbn());
            throw new DuplicacaoException();
        }

        Livro livro = new Livro();
        livro.setTitulo(livroRequestDTO.getTitulo());
        livro.setAutor(livroRequestDTO.getAutor());
        livro.setGenero(livroRequestDTO.getGenero());
        livro.setAnoPublicacao(livroRequestDTO.getAnoPublicacao());
        livro.setIsbn(livroRequestDTO.getIsbn());
        livro.setQuantidade(livroRequestDTO.getQuantidade());

        livroRepository.save(livro);
        log.info("Livro cadastrado com sucesso");
        return new LivroResponseDTO(livro);
    }

    public List<LivroResponseDTO> buscarLivros(String titulo, String autor, String genero, String isbn) {
        log.info("Iniciando processo de busca de livros");
        List<Livro> livros = livroRepository.buscarLivro(titulo, autor, genero, isbn);

        if (livros.isEmpty()) {
            log.error("Livro nao encontrado");
            throw new LivroNaoEncontradoException("Nenhum livro encontrado com este filtro");
        }

        List<LivroResponseDTO> livrosResponseDTO = livros.stream()
                .map(LivroResponseDTO::new) // Usando o construtor do LivroResponseDTO que recebe Livro
                .collect(Collectors.toList());

        log.info("Busca de livros Efetuada com sucesso");
        return livrosResponseDTO;
    }

    public void deletarLivro(Long id) {
        log.info("Iniciando processo de deletar livro");
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ID: {} é inexistente no banco de dados. Processo de deleção falhou.", id);
                    return new LivroNaoEncontradoException("ID de livro inexistente no banco de dados");
                });

        log.info("Livro deletado com sucesso");
        livroRepository.delete(livro);
    }

    public LivroResponseDTO atualizarLivro(Long id, LivroRequestDTO livroRequestDTO) {
        log.info("Iniciando processo de atualizar livro");
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isEmpty()) {
            log.error("ID: {} é inexistente no banco de dados. Processo de atualização falhou.", id);
            throw new LivroNaoEncontradoException("ID de livro inexistente no banco de dados");
        }

        Livro livroExistente = livro.get();

        log.info("Atualizando dados do livro com novos valores");
        livroExistente.setTitulo(livroRequestDTO.getTitulo());
        livroExistente.setAutor(livroRequestDTO.getAutor());
        livroExistente.setGenero(livroRequestDTO.getGenero());
        livroExistente.setIsbn(livroRequestDTO.getIsbn());
        livroExistente.setAnoPublicacao(livroRequestDTO.getAnoPublicacao());

        livroExistente = livroRepository.save(livroExistente);
        log.info("Livro atualizado com sucesso");

        return new LivroResponseDTO(livroExistente);
    }
}