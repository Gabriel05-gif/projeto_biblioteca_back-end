package com.gabriel.bibilioteca.business.services;

import com.gabriel.bibilioteca.controller.dtos.UsuarioRequestDTO;
import com.gabriel.bibilioteca.controller.dtos.UsuarioResponseDTO;
import com.gabriel.bibilioteca.entities.Usuario;
import com.gabriel.bibilioteca.infrastructure.exceptions.DuplicacaoException;
import com.gabriel.bibilioteca.infrastructure.exceptions.SenhaIncorretaException;
import com.gabriel.bibilioteca.infrastructure.exceptions.UsuarioInexistenteException;
import com.gabriel.bibilioteca.infrastructure.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO){
        log.info("Iniciando processo de cadastro do usuario.");
        if (usuarioRepository.findByEmail(usuarioRequestDTO.getEmail()).isPresent()){
            log.error("Já existe um usuario com esse email. email: {}", usuarioRequestDTO.getEmail());
            throw new DuplicacaoException();
        }

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setSenha(usuarioRequestDTO.getSenha());

        usuarioRepository.save(usuario);

        log.info("Cadastro finalizado com sucesso.");
        return new UsuarioResponseDTO(usuario);
    }

    public Boolean autenticarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Optional<Usuario> usuarioRequest = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());

        log.info("Iniciando processo de autenticação do usuario.");
        if (usuarioRequest.isEmpty()){
            log.error("Usuario inexistente. email: {}", usuarioRequestDTO.getEmail());
            throw new UsuarioInexistenteException();
        }

        if (!usuarioRequest.get().getSenha().equals(usuarioRequestDTO.getSenha())){
            log.error("Senha incorreta.");
            throw new SenhaIncorretaException();
        } else {
            log.info("Usuario autenticado com sucesso.");
            return true;
        }
    }
}
