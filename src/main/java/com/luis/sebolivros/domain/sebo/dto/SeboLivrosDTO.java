package com.luis.sebolivros.domain.sebo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.common.enums.Perfil;
import com.luis.sebolivros.domain.livro.dto.LivroDTO;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeboLivrosDTO implements Serializable {

    private Integer id;

    private String nome;

    private List<LivroDTO> livros = new ArrayList<>();

    public SeboLivrosDTO(){

    }

    public SeboLivrosDTO(Sebo obj) {
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
