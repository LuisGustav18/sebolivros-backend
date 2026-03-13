package com.luis.sebolivros.domain.sebo.resource;

import com.luis.sebolivros.domain.cliente.dto.ClienteDTO;
import com.luis.sebolivros.domain.cliente.entity.Cliente;
import com.luis.sebolivros.domain.cliente.service.ClienteService;
import com.luis.sebolivros.domain.sebo.dto.SeboDTO;
import com.luis.sebolivros.domain.sebo.dto.SeboLivrosDTO;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.service.SeboService;
import com.luis.sebolivros.infra.cep.dto.EnderecoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/sebos")
public class SeboResource {

    @Autowired
    private SeboService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SeboDTO> findById(@PathVariable Integer id){
        Sebo obj = service.findById(id);
        SeboDTO objDto = new SeboDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping(value = "/{id}/books")
    public ResponseEntity<SeboLivrosDTO> findBooks(@PathVariable Integer id){
        Sebo obj = service.findById(id);
        SeboLivrosDTO objDto = new SeboLivrosDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping
    public ResponseEntity<List<SeboDTO>> findAll(){
        List<Sebo> list = service.findAll();
        List<SeboDTO> listDto = list.stream().map(SeboDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<SeboDTO> create(@Valid @RequestBody SeboDTO objDto){
        Sebo obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SeboDTO> update(@PathVariable Integer id,@Valid @RequestBody SeboDTO objDto){
        Sebo obj = service.update(id, objDto);
        return ResponseEntity.ok().body(new SeboDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/endereco")
    public ResponseEntity<EnderecoDTO> findAddress(@PathVariable Integer id){
        EnderecoDTO objDto = service.findAddress(id);
        return ResponseEntity.ok().body(objDto);
    }
}
