package com.gabriel.bibilioteca.infrastructure.repositories;

import com.gabriel.bibilioteca.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
