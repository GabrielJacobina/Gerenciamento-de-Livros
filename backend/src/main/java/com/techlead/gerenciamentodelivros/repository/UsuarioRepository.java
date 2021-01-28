package com.techlead.gerenciamentodelivros.repository;

import com.techlead.gerenciamentodelivros.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByUsername(String username);

    Usuario findByUsername(String username);
}
