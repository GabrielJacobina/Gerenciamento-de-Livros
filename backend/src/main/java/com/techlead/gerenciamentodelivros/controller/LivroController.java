package com.techlead.gerenciamentodelivros.controller;

import com.techlead.gerenciamentodelivros.dto.LivroDTO;
import com.techlead.gerenciamentodelivros.model.Livro;
import com.techlead.gerenciamentodelivros.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @GetMapping
    public ResponseEntity<Page<Livro>> getAllContatos(Pageable pageable){
        return new ResponseEntity<>(livroService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getOneContato(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(livroService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Livro> createContato(@Valid @RequestBody LivroDTO livroDTO){
        return new ResponseEntity<>(livroService.save(livroDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContato(@PathVariable(value = "id") Long id){
        livroService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> replaceContato(@Valid @RequestBody LivroDTO livroDTO) {
        livroService.replace(livroDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
