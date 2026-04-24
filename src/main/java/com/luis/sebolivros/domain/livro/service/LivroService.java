package com.luis.sebolivros.domain.livro.service;

import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.autor.service.AutorService;
import com.luis.sebolivros.domain.estoque.repository.EstoqueRepository;
import com.luis.sebolivros.domain.estoque.service.EstoqueService;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.service.SeboService;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import com.luis.sebolivros.domain.editora.entity.Editora;
import com.luis.sebolivros.domain.editora.service.EditoraService;
import com.luis.sebolivros.domain.livro.dto.LivroDTO;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.livro.repository.LivroRepository;
import com.luis.sebolivros.infra.storage.SupaBaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import com.luis.sebolivros.exceptions.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private EstoqueRepository estoqueRepository;

    @Autowired
    private EditoraService editoraService;

    @Autowired
    private SupaBaseStorageService storageService;

    public Livro findById(int id){
        Optional<Livro> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrado"));
    }

    public List<Livro> findAll(){
        return repository.findAll();
    }

    public Livro create(LivroDTO objDto, MultipartFile file){
        objDto.setId(null);

        if (file != null && !file.isEmpty()) {
            String imageUrl = storageService.uploadImagem(file);
            objDto.setImageUrl(imageUrl);
        }


        Livro obj = newLivro(objDto);
        return repository.save(obj);
    }

    public Livro update(Integer id, LivroDTO objDto, MultipartFile file){
        objDto.setId(id);
        Livro oldObj = findById(id);

        if (estoqueRepository.existsByLivroId(id)){
            throw new DataIntegrityViolationException("Não é possível alterar livro em estoques");
        }

        if (file != null && !file.isEmpty()){
            String imageUrl = storageService.uploadImagem(file);
            objDto.setImageUrl(imageUrl);
        } else {
            objDto.setImageUrl(oldObj.getImageUrl());
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
        obj.setAutor(autor);
        obj.setEditora(editora);
        obj.setIsbn(objDto.getIsbn());
        obj.setAnoDeLancamento(objDto.getAnoDeLancamento());
        obj.setImageUrl(objDto.getImageUrl());
        return obj;
    }
}
