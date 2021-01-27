package com.techlead.gerenciamentodelivros.repository;

import com.techlead.gerenciamentodelivros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
