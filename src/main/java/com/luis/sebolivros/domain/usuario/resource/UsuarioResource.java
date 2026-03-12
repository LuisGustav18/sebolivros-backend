package com.luis.sebolivros.domain.usuario.resource;

import com.luis.sebolivros.domain.usuario.entity.Usuario;
import com.luis.sebolivros.domain.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/{email}")
    public ResponseEntity<Usuario> findByEmail(@PathVariable String email){
        Usuario obj = service.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }
}
