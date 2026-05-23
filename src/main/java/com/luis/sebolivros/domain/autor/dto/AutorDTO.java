package com.luis.sebolivros.domain.autor.dto;

import com.luis.sebolivros.domain.autor.entity.Autor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class AutorDTO implements Serializable {

    private Integer id;

    @NotNull(message = "O campo NOME é requerido")
    private String nome;

    private String imageUrl;

    public AutorDTO() {

    }

    public AutorDTO(String nome) {
        this.nome = nome;
    }

    public AutorDTO(Autor obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.imageUrl = obj.getImageUrl();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
