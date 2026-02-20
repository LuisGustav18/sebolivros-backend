package com.luis.sebolivros.domain.livro.resource;

import com.luis.sebolivros.domain.livro.dto.LivroDTO;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.livro.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/livros")
public class LivrosResource {

    @Autowired
    private LivroService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Integer id){
        Livro obj = service.findById(id);
        LivroDTO objDto = new LivroDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(){
        List<Livro> list = service.findAll();
        List<LivroDTO> listDto = list.stream().map(LivroDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @PreAuthorize("hasAnyRole('GESTOR')")
    @PostMapping
    public ResponseEntity<LivroDTO> create(@Valid @RequestBody LivroDTO objDto){
        Livro obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('GESTOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Integer id,@Valid @RequestBody LivroDTO objDto){
        Livro obj = service.update(id, objDto);
        return ResponseEntity.ok().body(new LivroDTO(obj));
    }
}
