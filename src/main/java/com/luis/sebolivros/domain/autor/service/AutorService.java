package com.luis.sebolivros.domain.autor.service;

import com.luis.sebolivros.domain.autor.dto.AutorDTO;
import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.autor.repository.AutorRepository;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public Autor findById(int id){
        Optional<Autor> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Autor não encontrado"));
    }

    public List<Autor> findAll(){
        return repository.findAll();
    }

    public Autor create(AutorDTO objDto){
        if (repository.findByNome(objDto.getNome()) != null){
            throw new DataIntegrityViolationException("Autor já cadastrado");
        }
        objDto.setId(null);
        return repository.save(new Autor(objDto));
    }

    public Autor update(Integer id, AutorDTO  objDto){
        objDto.setId(id);
        Autor oldObj = findById(id);
        oldObj = new Autor(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Autor obj = findById(id);
        if (obj.getLivros().isEmpty()){
            throw new DataIntegrityViolationException("Autor possui livros");
        }
        repository.delete(obj);
    }
}
