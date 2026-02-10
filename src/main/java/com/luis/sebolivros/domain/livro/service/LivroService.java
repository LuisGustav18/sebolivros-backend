package com.luis.sebolivros.domain.livro.service;

import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.autor.service.AutorService;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import com.luis.sebolivros.domain.editora.entity.Editora;
import com.luis.sebolivros.domain.editora.service.EditoraService;
import com.luis.sebolivros.domain.livro.dto.LivroDTO;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.livro.enums.Condicao;
import com.luis.sebolivros.domain.livro.enums.Estado;
import com.luis.sebolivros.domain.livro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private AutorService autorService;

    @Autowired
    private EditoraService editoraService;

    public Livro findById(int id){
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado"));
    }

    public List<Livro> findAll(){
        return repository.findAll();
    }

    public Livro create(LivroDTO objDto){
        objDto.setId(null);
        return repository.save(newLivro(objDto));
    }

    public Livro update(Integer id, LivroDTO objDto){
        objDto.setId(id);
        Livro oldObj = findById(id);
        oldObj = newLivro(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id){
        Livro obj = findById(id);
        repository.delete(obj);
    }

    private Livro newLivro(LivroDTO objDto) {
        Autor autor = autorService.findById(objDto.getAuto());
        Editora editora = editoraService.findById(objDto.getEditora());

        Livro obj = new Livro();

        if (objDto.getId() != null){
            obj.setId(objDto.getId());
        }

        obj.setTitulo(objDto.getTitulo());
        obj.setQuantidade(objDto.getQuantidade());
        obj.setAuto(autor);
        obj.setEditora(editora);
        obj.setIsbn(objDto.getIsbn());
        obj.setCondicao(Condicao.toEnum(objDto.getCondicao()));
        obj.setEstado(Estado.toenum(objDto.getEstado()));
        obj.setImageUrl(objDto.getImageUrl());
        return obj;
    }
}
