package com.techlead.gerenciamentodelivros.service;

import com.techlead.gerenciamentodelivros.config.JwtTokenProvider;
import com.techlead.gerenciamentodelivros.dto.UsuarioDTO;
import com.techlead.gerenciamentodelivros.exception.CustomException;
import com.techlead.gerenciamentodelivros.mapper.UsuarioMapper;
import com.techlead.gerenciamentodelivros.model.Token;
import com.techlead.gerenciamentodelivros.model.Usuario;
import com.techlead.gerenciamentodelivros.model.enums.Role;
import com.techlead.gerenciamentodelivros.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(usuarioRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public String save(UsuarioDTO usuarioDTO) {
        if (!usuarioRepository.existsByUsername(usuarioDTO.getUsername())) {
            List<Role> roles = new ArrayList<>();
            roles.add(Role.ROLE_CLIENT);
            usuarioDTO.setAuthorities(roles);
            log.info(usuarioDTO);
            Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioDTO);
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioRepository.save(usuario);
            return jwtTokenProvider.createToken(usuario.getUsername(), usuario.getAuthorities());
        } else {
            throw new CustomException("Username já está sendo utilizado", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Usuario findByUsername(UserDetails userDetails) {
        log.info(userDetails);
        return usuarioRepository.findByUsername(userDetails.getUsername());
    }

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, usuarioRepository.findByUsername(username).getAuthorities());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, usuarioRepository.findByUsername(username).getAuthorities());
    }
    
    public Token login(String username, String password) {
        String textToken = this.signin(username, password);
        Token token = new Token();
        token.setToken(textToken);
        log.info(passwordEncoder.encode("admin"));
        return token;
    }
}
