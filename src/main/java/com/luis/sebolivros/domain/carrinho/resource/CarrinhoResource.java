package com.luis.sebolivros.domain.carrinho.resource;

import com.luis.sebolivros.domain.carrinho.dto.CarrinhoDTO;
import com.luis.sebolivros.domain.carrinho.entity.Carrinho;
import com.luis.sebolivros.domain.carrinho.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "carrinhos")
public class CarrinhoResource {

    @Autowired
    private CarrinhoService service;

    @GetMapping()
    public ResponseEntity<List<CarrinhoDTO>> findAll(){
        List<Carrinho> list = service.findAll();
        List<CarrinhoDTO> listDto = list.stream().map(CarrinhoDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarrinhoDTO> findById(@PathVariable int id){
        Carrinho obj = service.findById(id);
        return ResponseEntity.ok().body(new CarrinhoDTO(obj));
    }

    @PostMapping(value = "/{id}/finalizar")
    public ResponseEntity<CarrinhoDTO> finalizar(@PathVariable int id){
        Carrinho obj = service.finalizar(id);
        return ResponseEntity.ok().body(new CarrinhoDTO(obj));
    }

}
