package com.luis.sebolivros.domain.itemCarrinho.resource;

import com.luis.sebolivros.domain.itemCarrinho.dto.ItemCarrinhoDTO;
import com.luis.sebolivros.domain.itemCarrinho.entity.ItemCarrinho;
import com.luis.sebolivros.domain.itemCarrinho.service.ItemCarrinhoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "ItensCarrinho")
public class ItemCarrinhoResource {

    @Autowired
    private ItemCarrinhoService service;

    @GetMapping()
    public ResponseEntity<List<ItemCarrinhoDTO>> findAll(){
        List<ItemCarrinho> list = service.findAll();
        List<ItemCarrinhoDTO> listDto = list.stream().map(ItemCarrinhoDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemCarrinhoDTO> findById(@PathVariable int id){
        ItemCarrinho obj = service.findById(id);
        return ResponseEntity.ok().body(new ItemCarrinhoDTO(obj));
    }

    @PostMapping
    public ResponseEntity<ItemCarrinhoDTO> create(@Valid @RequestBody ItemCarrinhoDTO objDto){
        ItemCarrinho obj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ItemCarrinhoDTO> update(@PathVariable int id,@Valid @RequestBody ItemCarrinhoDTO objDto){
        ItemCarrinho obj = service.update(id, objDto);
        return ResponseEntity.ok().body(new ItemCarrinhoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ItemCarrinhoDTO> delete(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
