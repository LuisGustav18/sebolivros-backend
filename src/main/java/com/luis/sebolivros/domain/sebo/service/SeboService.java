package com.luis.sebolivros.domain.sebo.service;

import com.luis.sebolivros.domain.cliente.entity.Cliente;
import com.luis.sebolivros.domain.cliente.repository.ClienteRepository;
import com.luis.sebolivros.domain.common.exceptions.ObjectNotFoundException;
import com.luis.sebolivros.domain.sebo.dto.SeboDTO;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.repository.SeboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeboService {

    @Autowired
    private SeboRepository repository;

    @Autowired
    private ClienteRepository clienteRepository ;

    public Sebo findById(int id){
        Optional<Sebo> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Sebo não encontrado"));
    }

    public List<Sebo> findAll(){
        return repository.findAll();
    }

    public Sebo create(SeboDTO objDto){
        objDto.setId(null);
        validarEmail(objDto);
        return repository.save(new Sebo(objDto));
    }

    public Sebo update(Integer id, SeboDTO objDto){
        objDto.setId(id);
        validarEmail(objDto);
        Sebo oldObj = findById(id);
        oldObj = new Sebo(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Sebo obj = findById(id);
        repository.delete(obj);
    }

    private void validarEmail(SeboDTO objDto){
        Optional<Sebo> cliente = repository.findByEmail(objDto.getEmail());
        Optional<Cliente> sebo = clienteRepository.findByEmail(objDto.getEmail());

        if (cliente.isPresent() || sebo.isPresent()){
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
    }
}
