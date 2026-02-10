package com.luis.sebolivros.domain.editora.service;

import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import com.luis.sebolivros.domain.editora.dto.EditoraDTO;
import com.luis.sebolivros.domain.editora.entity.Editora;
import com.luis.sebolivros.domain.editora.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository repository;

    public Editora findById(int id){
        Optional<Editora> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Editora não encontrado"));
    }

    public List<Editora> findAll(){
        return repository.findAll();
    }

    public Editora create(EditoraDTO objDto){
        objDto.setId(null);
        return repository.save(new Editora(objDto));
    }

    public Editora update(Integer id, EditoraDTO objDto){
        objDto.setId(id);
        Editora oldObj = findById(id);
        oldObj = new Editora(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Editora obj = findById(id);
        repository.delete(obj);
    }
}
