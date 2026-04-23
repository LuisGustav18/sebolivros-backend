package com.luis.sebolivros.domain.estoque.resource;

import com.luis.sebolivros.domain.estoque.dto.EstoqueDTO;
import com.luis.sebolivros.domain.estoque.entity.Estoque;
import com.luis.sebolivros.domain.estoque.service.EstoqueService;
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
@RequestMapping(value = "/estoques")
public class EstoqueResource {

    @Autowired
    private EstoqueService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EstoqueDTO> findById(@PathVariable Integer id){
        Estoque obj = service.findById(id);
        EstoqueDTO objDto = new EstoqueDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> findAll(){
        List<Estoque> list = service.findAll();
        List<EstoqueDTO> listDto = list.stream().map(EstoqueDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @PreAuthorize("hasAnyRole('GESTOR')")
    @PostMapping
    public ResponseEntity<EstoqueDTO> create(@Valid @ModelAttribute EstoqueDTO objDto, @RequestParam(value = "file", required = false) MultipartFile file){
        Estoque obj = service.create(objDto, file);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('GESTOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<EstoqueDTO> update(@PathVariable Integer id,@Valid @ModelAttribute EstoqueDTO objDto, @RequestParam(value = "file", required = false) MultipartFile file){
        Estoque obj = service.update(id, objDto, file);
        return ResponseEntity.ok().body(new EstoqueDTO(obj));
    }
}
