package com.techlead.gerenciamentodelivros.mapper;

import com.techlead.gerenciamentodelivros.dto.LivroDTO;
import com.techlead.gerenciamentodelivros.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class LivroMapper {
    public static final LivroMapper INSTANCE  = Mappers.getMapper(LivroMapper.class);

    public abstract Livro toLivro(LivroDTO livroDTO);
}
