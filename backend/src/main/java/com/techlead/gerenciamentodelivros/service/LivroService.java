package com.techlead.gerenciamentodelivros.service;

import com.techlead.gerenciamentodelivros.dto.LivroDTO;
import com.techlead.gerenciamentodelivros.mapper.LivroMapper;
import com.techlead.gerenciamentodelivros.model.Livro;
import com.techlead.gerenciamentodelivros.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public Page<Livro> listAll(Pageable pageable) {
        return livroRepository.findAll(pageable);
    }

    public Livro findById(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livro não encontrado"));
    }

    public Livro save(LivroDTO livroDTO) {
        return livroRepository.save(LivroMapper.INSTANCE.toLivro(livroDTO));
    }

    public void replace(LivroDTO livroDTO) {
        Livro livroSalvo = findById(livroDTO.getId());
        Livro livro = LivroMapper.INSTANCE.toLivro(livroDTO);
        livro.setId(livroSalvo.getId());
        livroRepository.save(livro);
    }

    public void delete(Long id) {
        livroRepository.delete(findById(id));
    }


}
