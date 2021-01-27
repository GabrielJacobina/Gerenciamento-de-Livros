package com.techlead.gerenciamentodelivros.service;

import com.techlead.gerenciamentodelivros.dto.UsuarioDTO;
import com.techlead.gerenciamentodelivros.mapper.UsuarioMapper;
import com.techlead.gerenciamentodelivros.model.Usuario;
import com.techlead.gerenciamentodelivros.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(usuarioRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public Usuario save(UsuarioDTO usuarioDTO) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        usuarioDTO.setAuthorities("ROLE_USER");
        log.info(usuarioDTO);
        Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario findByUsername(UserDetails userDetails) {
        log.info(userDetails);
        return usuarioRepository.findByUsername(userDetails.getUsername());
    }
}
