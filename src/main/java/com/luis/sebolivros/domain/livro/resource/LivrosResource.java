package com.luis.sebolivros.domain.livro.resource;

import com.luis.sebolivros.domain.livro.dto.LivroDTO;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.livro.service.LivroService;
import com.luis.sebolivros.infra.storage.SupaBaseStorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public ResponseEntity<LivroDTO> create(@Valid @ModelAttribute LivroDTO objDto, @RequestParam(value = "file", required = false) MultipartFile file){
        Livro obj = service.create(objDto, file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('GESTOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Integer id,@Valid @ModelAttribute LivroDTO objDto, @RequestParam(value = "file", required = false) MultipartFile file){
        Livro obj = service.update(id, objDto, file);
        return ResponseEntity.ok().body(new LivroDTO(obj));
    }
}
