package com.luis.sebolivros.domain.estoque.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.estoque.entity.Estoque;
import com.luis.sebolivros.domain.estoque.enums.Condicao;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class EstoqueDTO implements Serializable {

    private Integer id;

    @NotNull(message = "O campo PREÇO é requerido")
    private Double preco;

    @NotNull(message = "O campo QUANTIDADE é requerido")
    private Integer quantidade;

    @NotNull(message = "O campo CONDIÇÃO é requerido")
    private Integer condicao;

    private String imageUrl;

    @NotNull(message = "O campo LIVRO é requerido")
    private Integer livro;

    @NotNull(message = "O campo SEBO é requerido")
    private Integer sebo;

    private String nomeSebo;

    private String tituloLivro;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public EstoqueDTO(){
    }

    public EstoqueDTO(Estoque obj) {
        this.id = obj.getId();
        this.quantidade = obj.getQuantidade();
        this.condicao = obj.getCondicao().getCodigo();
        this.imageUrl = obj.getImageUrl();
        this.preco = obj.getPreco();
        this.livro = obj.getLivro().getId();
        this.sebo = obj.getSebo().getId();
        this.nomeSebo = obj.getSebo().getNome();
        this.tituloLivro = obj.getLivro().getTitulo();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getCondicao() {
        return condicao;
    }

    public void setCondicao(Integer condicao) {
        this.condicao = condicao;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getLivro() {
        return livro;
    }

    public void setLivro(Integer livro) {
        this.livro = livro;
    }

    public Integer getSebo() {
        return sebo;
    }

    public void setSebo(Integer sebo) {
        this.sebo = sebo;
    }

    public String getNomeSebo() {
        return nomeSebo;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
