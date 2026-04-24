package com.luis.sebolivros.domain.sebo.dto;

import com.luis.sebolivros.domain.estoque.dto.EstoqueDTO;
import com.luis.sebolivros.domain.sebo.entity.Sebo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SeboLivrosDTO implements Serializable {

    private Integer id;

    private String nome;

    private List<EstoqueDTO> estoques = new ArrayList<>();

    public SeboLivrosDTO(){

    }

    public SeboLivrosDTO(Sebo obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.estoques = obj.getEstoques().stream().map(EstoqueDTO::new).toList();
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

    public List<EstoqueDTO> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<EstoqueDTO> estoques) {
        this.estoques = estoques;
    }
}
