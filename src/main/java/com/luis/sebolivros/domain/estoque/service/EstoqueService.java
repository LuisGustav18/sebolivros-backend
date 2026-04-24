package com.luis.sebolivros.domain.estoque.service;

import com.luis.sebolivros.domain.estoque.dto.EstoqueDTO;
import com.luis.sebolivros.domain.estoque.entity.Estoque;
import com.luis.sebolivros.domain.estoque.enums.Condicao;
import com.luis.sebolivros.domain.estoque.repository.EstoqueRepository;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.livro.service.LivroService;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import com.luis.sebolivros.domain.sebo.service.SeboService;
import com.luis.sebolivros.exceptions.DataIntegrityViolationException;
import com.luis.sebolivros.exceptions.ObjectNotFoundException;
import com.luis.sebolivros.infra.storage.SupaBaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository repository;

    @Autowired
    private SeboService seboService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private SupaBaseStorageService storageService;

    public Estoque findById(int id){
        Optional<Estoque> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Estoque não encontrado"));
    }

    public List<Estoque> findAll(){
        return repository.findAll();
    }

    public Estoque create(EstoqueDTO objDto, MultipartFile file){
        objDto.setId(null);

        if (file != null && !file.isEmpty()) {
            String imageUrl = storageService.uploadImagem(file);
            objDto.setImageUrl(imageUrl);
        }


        Estoque obj = newEstoque(objDto);
        return repository.save(obj);
    }

    public Estoque update(Integer id, EstoqueDTO objDto, MultipartFile file){
        objDto.setId(id);
        Estoque oldObj = findById(id);

        if (oldObj.getSebo().getId() != objDto.getSebo()){
            throw new DataIntegrityViolationException("Não e possível mudar local de cadastro");
        }

        if (file != null && !file.isEmpty()){
            String imageUrl = storageService.uploadImagem(file);
            objDto.setImageUrl(imageUrl);
        } else {
            objDto.setImageUrl(oldObj.getImageUrl());
        }

        oldObj = newEstoque(objDto);
        return repository.save(oldObj);
    }



    private Estoque newEstoque(EstoqueDTO objDto) {
        Sebo sebo = seboService.findById(objDto.getSebo());
        Livro livro = livroService.findById(objDto.getLivro());

        Estoque obj = new Estoque();

        if (objDto.getId() != null){
            obj.setId(objDto.getId());
        }

        obj.setPreco(objDto.getPreco());
        obj.setQuantidade(objDto.getQuantidade());
        obj.setCondicao(Condicao.toEnum(objDto.getCondicao()));
        obj.setImageUrl(objDto.getImageUrl());
        obj.setLivro(livro);
        obj.setSebo(sebo);
        return obj;
    }
}
