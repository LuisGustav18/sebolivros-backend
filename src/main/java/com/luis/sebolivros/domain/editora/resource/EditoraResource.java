package com.luis.sebolivros.domain.editora.resource;

import com.luis.sebolivros.domain.editora.dto.EditoraDTO;
import com.luis.sebolivros.domain.editora.entity.Editora;
import com.luis.sebolivros.domain.editora.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/editoras")
public class EditoraResource {

    @Autowired
    private EditoraService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EditoraDTO> findById(@PathVariable Integer id){
        Editora obj = service.findById(id);
        EditoraDTO objDto = new EditoraDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping
    public ResponseEntity<List<EditoraDTO>> findAll(){
        List<Editora> list = service.findAll();
        List<EditoraDTO> listDto = list.stream().map(EditoraDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<EditoraDTO> create(@RequestBody EditoraDTO objDto){
        Editora obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EditoraDTO> update(@PathVariable Integer id, @RequestBody EditoraDTO objDto){
        Editora obj = service.update(id, objDto);
        return ResponseEntity.ok().body(new EditoraDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
