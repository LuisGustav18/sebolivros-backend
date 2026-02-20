package com.luis.sebolivros.domain.common.service;

import com.luis.sebolivros.SebolivrosApplication;
import com.luis.sebolivros.domain.cliente.entity.Cliente;
import com.luis.sebolivros.domain.cliente.repository.ClienteRepository;
import com.luis.sebolivros.domain.cliente.service.ClienteService;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.repository.SeboRepository;
import com.luis.sebolivros.domain.usuario.entity.Usuario;
import com.luis.sebolivros.domain.usuario.repository.UsuarioRepository;
import com.luis.sebolivros.security.User;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDatailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    // Criamos o user que será logado no nosso sistema
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> user = repository.findByEmail(email);
        if(user.isPresent()){
            return new User(user.get().getEmail(), user.get().getSenha(), user.get().getPerfil());
        }
        throw new UsernameNotFoundException(email);
    }
}
