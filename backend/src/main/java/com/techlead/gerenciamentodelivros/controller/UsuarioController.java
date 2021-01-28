package com.techlead.gerenciamentodelivros.controller;

import com.techlead.gerenciamentodelivros.dto.UsuarioDTO;
import com.techlead.gerenciamentodelivros.service.UsuarioDetailsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioDetailsService usuarioDetailsService;

    @PostMapping
    public ResponseEntity<String> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
        return new ResponseEntity<>(usuarioDetailsService.save(usuarioDTO), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    @ApiOperation(value = "${UsuarioController.signin}")
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public ResponseEntity<String> login(//
                                @ApiParam("Username") @RequestParam String username, //
                                @ApiParam("Password") @RequestParam String password) {
        return new ResponseEntity<>(usuarioDetailsService.signin(username, password), HttpStatus.OK);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<String> refresh(HttpServletRequest req) {
        return new ResponseEntity<>(usuarioDetailsService.refresh(req.getRemoteUser()), HttpStatus.OK);
    }
}
