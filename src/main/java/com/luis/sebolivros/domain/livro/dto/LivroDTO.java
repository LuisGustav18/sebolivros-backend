package com.luis.sebolivros.domain.livro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.livro.entity.Livro;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class LivroDTO implements Serializable {
    
    private Integer id;

    @NotNull(message = "O campo TITULO é requerido")
    private String titulo;

    @NotNull(message = "O campo AUTOR é requerido")
    private Integer autor;

    @NotNull(message = "O campo ANO DE LANÇAMENTO é requerido")
    private int anoDeLancamento;

    @NotNull(message = "O campo EDITORA é requerido")
    private Integer editora;

    @NotNull(message = "O campo ISBN é requerido")
    private String isbn;

    @NotNull(message = "O campo ESTADO é requerido")
    private Integer sebo;

    private String imageUrl;

    private String nomeAutor;

    private String nomeEditora;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public LivroDTO(){

    }

    public LivroDTO(Livro obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.autor = obj.getAutor().getId();
        this.anoDeLancamento = obj.getAnoDeLancamento();
        this.editora = obj.getEditora().getId();
        this.isbn = obj.getIsbn();
        this.imageUrl = obj.getImageUrl();
        this.nomeAutor = obj.getAutor().getNome();
        this.nomeEditora = obj.getEditora().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAutor() {
        return autor;
    }

    public void setAutor(Integer autor) {
        this.autor = autor;
    }

    public Integer getEditora() {
        return editora;
    }

    public void setEditora(Integer editora) {
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public Integer getSebo() {
        return sebo;
    }

    public void setSebo(Integer sebo) {
        this.sebo = sebo;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getNomeEditora() {
        return nomeEditora;
    }

    public void setNomeEditora(String nomeEditora) {
        this.nomeEditora = nomeEditora;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }
}
