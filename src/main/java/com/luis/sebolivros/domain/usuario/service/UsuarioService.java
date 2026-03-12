package com.luis.sebolivros.domain.usuario.service;

import com.luis.sebolivros.domain.usuario.entity.Usuario;
import com.luis.sebolivros.domain.usuario.repository.UsuarioRepository;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario findByEmail(String email){
        return this.repository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }
}
