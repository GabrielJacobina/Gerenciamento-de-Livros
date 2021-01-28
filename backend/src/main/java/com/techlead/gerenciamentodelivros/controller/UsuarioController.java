package com.techlead.gerenciamentodelivros.controller;

import com.techlead.gerenciamentodelivros.dto.UsuarioDTO;
import com.techlead.gerenciamentodelivros.model.Usuario;
import com.techlead.gerenciamentodelivros.service.UsuarioDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioDetailsService usuarioDetailsService;

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
        return new ResponseEntity<>(usuarioDetailsService.save(usuarioDTO), HttpStatus.CREATED);
    }
}
