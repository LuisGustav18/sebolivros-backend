package com.luis.sebolivros.domain.livro.service;

import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.autor.service.AutorService;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.resource.SeboResource;
import com.luis.sebolivros.domain.sebo.service.SeboService;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import com.luis.sebolivros.domain.editora.entity.Editora;
import com.luis.sebolivros.domain.editora.service.EditoraService;
import com.luis.sebolivros.domain.livro.dto.LivroDTO;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.livro.enums.Condicao;
import com.luis.sebolivros.domain.livro.enums.Estado;
import com.luis.sebolivros.domain.livro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    private SeboService seboService;

    @Autowired
    private EditoraService editoraService;

    public Livro findById(int id){
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado"));
    }

    public Livro findByIdAndSeboId(int id, int seboId){
        Optional<Livro> obj = repository.findByIdAndSeboId(id, seboId);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado no sebo"));
    }

    public List<Livro> findAll(){
        return repository.findAll();
    }

    public Livro create(LivroDTO objDto){
        objDto.setId(null);
        Livro obj = newLivro(objDto);
        return repository.save(obj);
    }

    public Livro update(Integer id, LivroDTO objDto){
        objDto.setId(id);
        Livro oldObj = findById(id);

        if (oldObj.getSebo().getId() != objDto.getSebo()){
            throw new DataIntegrityViolationException("Não e possível mudar local de cadastro");
        }

        oldObj = newLivro(objDto);
        return repository.save(oldObj);
    }



    private Livro newLivro(LivroDTO objDto) {
        Autor autor = autorService.findById(objDto.getAutor());
        Editora editora = editoraService.findById(objDto.getEditora());
        Sebo sebo = seboService.findById(objDto.getSebo());

        Livro obj = new Livro();

        if (objDto.getId() != null){
            obj.setId(objDto.getId());
        }

        obj.setTitulo(objDto.getTitulo());
        obj.setQuantidade(objDto.getQuantidade());
        obj.setAutor(autor);
        obj.setEditora(editora);
        obj.setIsbn(objDto.getIsbn());
        obj.setCondicao(Condicao.toEnum(objDto.getCondicao()));
        obj.setEstado(Estado.toEnum(objDto.getEstado()));
        obj.setImageUrl(objDto.getImageUrl());
        obj.setSebo(sebo);
        return obj;
    }
}
