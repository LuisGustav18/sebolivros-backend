package com.luis.sebolivros.domain.autor.resource;

import com.luis.sebolivros.domain.autor.dto.AutorDTO;
import com.luis.sebolivros.domain.autor.dto.AutorLivrosDTO;
import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.autor.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/autores")
public class AutorResource {

    @Autowired
    private AutorService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable Integer id){
        Autor obj = service.findById(id);
        AutorDTO objDto = new AutorDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping(value = "/{id}/books")
    public ResponseEntity<AutorLivrosDTO> findBooks(@PathVariable Integer id){
        Autor obj = service.findById(id);
        AutorLivrosDTO objDto = new AutorLivrosDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> findAll(){
        List<Autor> list = service.findAll();
        List<AutorDTO> listDto = list.stream().map(AutorDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<AutorDTO> create(@Valid @RequestBody AutorDTO objDto){
        Autor obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AutorDTO> update(@PathVariable Integer id,@Valid @RequestBody AutorDTO objDto){
        Autor obj = service.update(id, objDto);
        return ResponseEntity.ok().body(new AutorDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
