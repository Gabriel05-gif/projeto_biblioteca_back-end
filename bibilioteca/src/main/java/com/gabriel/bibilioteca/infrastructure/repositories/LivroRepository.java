package com.gabriel.bibilioteca.infrastructure.repositories;

import com.gabriel.bibilioteca.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIsbn(String isbn);

    @Query("SELECT l FROM Livro l WHERE " +
            "(:titulo IS NULL OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) " +
            "AND (:autor IS NULL OR LOWER(l.autor) LIKE LOWER(CONCAT('%', :autor, '%'))) " +
            "AND (:isbn IS NULL OR l.isbn = :isbn) " +
            "AND (:genero IS NULL OR LOWER(l.genero) LIKE LOWER(CONCAT('%', :genero, '%')))")
    public List<Livro> buscarLivro(@Param("titulo") String titulo,
                                   @Param("autor") String autor,
                                   @Param("genero") String genero,
                                   @Param("isbn") String isbn);

}

