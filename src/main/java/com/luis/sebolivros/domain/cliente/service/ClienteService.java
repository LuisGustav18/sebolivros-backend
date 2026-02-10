package com.luis.sebolivros.domain.cliente.service;

import com.luis.sebolivros.domain.cliente.dto.ClienteDTO;
import com.luis.sebolivros.domain.cliente.entity.Cliente;
import com.luis.sebolivros.domain.cliente.repository.ClienteRepository;
import com.luis.sebolivros.domain.common.exceptions.ObjectNotFoundException;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.repository.SeboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private SeboRepository seboRepository;

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
        return repository.save(new Cliente(objDto));
    }

    public Cliente update(Integer id, ClienteDTO objDto){
        objDto.setId(id);
        validarEmail(objDto);
        Cliente oldObj = findById(id);
        oldObj = new Cliente(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Cliente obj = findById(id);
        repository.delete(obj);
    }

    private void validarEmail(ClienteDTO objDto){
        Optional<Cliente> cliente = repository.findByEmail(objDto.getEmail());
        Optional<Sebo> sebo = seboRepository.findByEmail(objDto.getEmail());

        if (cliente.isPresent() || sebo.isPresent()){
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
    }
}
