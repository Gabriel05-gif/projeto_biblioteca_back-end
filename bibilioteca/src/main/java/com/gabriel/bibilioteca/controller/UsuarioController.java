package com.gabriel.bibilioteca.controller;

import com.gabriel.bibilioteca.business.services.UsuarioService;
import com.gabriel.bibilioteca.controller.dtos.UsuarioRequestDTO;
import com.gabriel.bibilioteca.controller.dtos.UsuarioResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.cadastrarUsuario(usuarioRequestDTO);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Void> autenticarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        usuarioService.autenticarUsuario(usuarioRequestDTO);
        return ResponseEntity.ok().build();
    }
}
