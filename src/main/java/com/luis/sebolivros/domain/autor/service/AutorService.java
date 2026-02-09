package com.luis.sebolivros.domain.autor.service;

import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.autor.repository.AutorRepository;
import com.luis.sebolivros.domain.common.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
}
