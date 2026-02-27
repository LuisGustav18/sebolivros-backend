package com.luis.sebolivros.domain.cliente.service;

import com.luis.sebolivros.domain.cliente.dto.ClienteDTO;
import com.luis.sebolivros.domain.cliente.entity.Cliente;
import com.luis.sebolivros.domain.cliente.repository.ClienteRepository;
import com.luis.sebolivros.domain.usuario.entity.Usuario;
import com.luis.sebolivros.domain.usuario.repository.UsuarioRepository;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.repository.SeboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.luis.sebolivros.exceptions.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Cliente findById(int id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDto){
        objDto.setId(null);
        validarEmail(objDto);
        objDto.setSenha(encoder.encode(objDto.getSenha()));
        return repository.save(new Cliente(objDto));
    }

    public Cliente update(Integer id, ClienteDTO objDto){
        objDto.setId(id);
        validarEmail(objDto);
        Cliente oldObj = findById(id);

        if (!objDto.getSenha().equals(oldObj.getSenha())){
            objDto.setSenha(encoder.encode(objDto.getSenha()));
        }

        oldObj = new Cliente(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Cliente obj = findById(id);
        repository.delete(obj);
    }

    private void validarEmail(ClienteDTO objDto){
        Optional<Usuario> obj = usuarioRepository.findByEmail(objDto.getEmail());

        if (obj.isPresent()){
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
    }
}
