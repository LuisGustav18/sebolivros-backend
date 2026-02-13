package com.luis.sebolivros.domain.autor.dto;

import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.livro.dto.LivroDTO;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AutorLivrosDTO implements Serializable {

    private Integer id;

    private String nome;

    private List<LivroDTO> livros = new ArrayList<>();

    public AutorLivrosDTO() {

    }

    public AutorLivrosDTO(Autor obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.livros = obj.getLivros().stream().map(LivroDTO::new).toList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LivroDTO> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroDTO> livros) {
        this.livros = livros;
    }
}
