package com.techlead.gerenciamentodelivros.dto;

import com.techlead.gerenciamentodelivros.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LivroDTO {

    private Long id;
    private String nome;
    private LocalDate dataDeCadastro;
    private Usuario idUsuario;
}
