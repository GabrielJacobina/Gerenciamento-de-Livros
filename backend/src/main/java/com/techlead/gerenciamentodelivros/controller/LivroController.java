package com.techlead.gerenciamentodelivros.controller;

import com.techlead.gerenciamentodelivros.dto.LivroDTO;
import com.techlead.gerenciamentodelivros.model.Livro;
import com.techlead.gerenciamentodelivros.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("livros")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @GetMapping
    public ResponseEntity<Page<Livro>> getAllLivros(Pageable pageable){
        return new ResponseEntity<>(livroService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getOneLivro(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(livroService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Livro> createLivro(@Valid @RequestBody LivroDTO livroDTO,
                                             @AuthenticationPrincipal UserDetails userDetails){
        return new ResponseEntity<>(livroService.save(livroDTO, userDetails), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable(value = "id") Long id,
                                            @AuthenticationPrincipal UserDetails userDetails){
        livroService.delete(id, userDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteLivroAdmin(@PathVariable(value = "id") Long id){
        livroService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> replaceLivro(@Valid @RequestBody LivroDTO livroDTO,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        livroService.replace(livroDTO, userDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Livro> replaceLivroAdmin(@Valid @RequestBody LivroDTO livroDTO) {
        livroService.replaceAdmin(livroDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
