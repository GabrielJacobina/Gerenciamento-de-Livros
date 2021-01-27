package com.techlead.gerenciamentodelivros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {

    private Long id;
    private String nome;
    private LocalDate dataDeCadastro;
}
