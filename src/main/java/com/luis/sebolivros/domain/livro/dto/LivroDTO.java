package com.luis.sebolivros.domain.livro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.livro.entity.Livro;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class LivroDTO implements Serializable {
    
    private int id;

    @NotNull(message = "O campo TITULO é requerido")
    private String titulo;

    @NotNull(message = "O campo QUANTIDADE é requerido")
    private int quantidade;

    @NotNull(message = "O campo AUTOR é requerido")
    private Integer auto;

    @NotNull(message = "O campo EDITORA é requerido")
    private Integer editora;

    @NotNull(message = "O campo ISBN é requerido")
    private int isbn;

    @NotNull(message = "O campo CONDIÇÃO é requerido")
    private Integer condicao;

    @NotNull(message = "O campo ESTADO é requerido")
    private Integer estado;

    private String imageUrl;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public LivroDTO(){

    }

    public LivroDTO(Livro obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.quantidade = obj.getQuantidade();
        this.auto = obj.getAuto().getId();
        this.editora = obj.getEditora().getId();
        this.isbn = obj.getIsbn();
        this.condicao = obj.getCondicao().getCodigo();
        this.estado = obj.getEstado().getCodigo();
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getAuto() {
        return auto;
    }

    public void setAuto(Integer auto) {
        this.auto = auto;
    }

    public Integer getEditora() {
        return editora;
    }

    public void setEditora(Integer editora) {
        this.editora = editora;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public Integer getCondicao() {
        return condicao;
    }

    public void setCondicao(Integer condicao) {
        this.condicao = condicao;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        estado = estado;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
}
