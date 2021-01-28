package com.techlead.gerenciamentodelivros.dto;

import com.techlead.gerenciamentodelivros.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String username;
    private String password;
    private List<Role> authorities;
}
