package com.luis.sebolivros.domain.autor.service;

import com.luis.sebolivros.domain.autor.dto.AutorDTO;
import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.autor.repository.AutorRepository;
import com.luis.sebolivros.exceptions.DataIntegrityViolationException;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import com.luis.sebolivros.infra.storage.SupaBaseStorageService;
import org.hibernate.cache.spi.support.StorageAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    @Autowired
    private SupaBaseStorageService storageService;

    public Autor findById(int id){
        Optional<Autor> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Autor não encontrado"));
    }

    public List<Autor> findAll(){
        return repository.findAll();
    }

    public Autor create(AutorDTO objDto, MultipartFile file){
        objDto.setId(null);

        if (file != null && !file.isEmpty()) {
            String imageUrl = storageService.uploadImagem(file, "autores");
            objDto.setImageUrl(imageUrl);
        }

        return repository.save(new Autor(objDto));
    }

    public Autor update(Integer id, AutorDTO  objDto, MultipartFile file){
        objDto.setId(id);
        Autor oldObj = findById(id);

        if (file != null && !file.isEmpty()) {
            String imageUrl = storageService.uploadImagem(file, "autores");
            objDto.setImageUrl(imageUrl);
        }
        else {
            objDto.setImageUrl(oldObj.getImageUrl());
        }

        oldObj = new Autor(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Autor obj = findById(id);
        if (!obj.getLivros().isEmpty()){
            throw new DataIntegrityViolationException("Autor possui livros");
        }
        repository.delete(obj);
    }
}
