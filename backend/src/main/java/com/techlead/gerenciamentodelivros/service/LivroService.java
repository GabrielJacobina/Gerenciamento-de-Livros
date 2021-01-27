package com.techlead.gerenciamentodelivros.service;

import com.techlead.gerenciamentodelivros.dto.LivroDTO;
import com.techlead.gerenciamentodelivros.mapper.LivroMapper;
import com.techlead.gerenciamentodelivros.model.Livro;
import com.techlead.gerenciamentodelivros.model.Usuario;
import com.techlead.gerenciamentodelivros.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    private final UsuarioDetailsService usuarioDetailsService;

    public Page<Livro> listAll(Pageable pageable) {
        return livroRepository.findAll(pageable);
    }

    public Livro findById(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livro não encontrado"));
    }

    public Livro save(LivroDTO livroDTO, UserDetails userDetails) {
        Usuario usuario = usuarioDetailsService.findByUsername(userDetails);
        livroDTO.setIdUsuario(usuario);
        livroDTO.setDataDeCadastro(LocalDate.now());
        return livroRepository.save(LivroMapper.INSTANCE.toLivro(livroDTO));
    }

    public void replace(LivroDTO livroDTO, UserDetails userDetails) {
        Livro livroSalvo = findById(livroDTO.getId());
        if (livroSalvo.getIdUsuario().getUsername().equals(userDetails.getUsername())) {
            Livro livro = LivroMapper.INSTANCE.toLivro(livroDTO);
            livro.setId(livroSalvo.getId());
            livroRepository.save(livro);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Não é possível realizar essa operação");
        }
    }

    public void delete(Long id, UserDetails userDetails) {
        Livro livro = findById(id);
        if (livro.getIdUsuario().getUsername().equals(userDetails.getUsername())) {
            livroRepository.delete(findById(id));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Não é possível realizar essa operação");
        }
    }


    public void deleteAdmin(Long id) {
        livroRepository.delete(findById(id));
    }

    public void replaceAdmin(LivroDTO livroDTO) {
        Livro livroSalvo = findById(livroDTO.getId());
        Livro livro = LivroMapper.INSTANCE.toLivro(livroDTO);
        livro.setId(livroSalvo.getId());
        livroRepository.save(livro);
    }
}
