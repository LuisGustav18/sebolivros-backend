package com.luis.sebolivros.domain.autor.dto;

import com.luis.sebolivros.domain.autor.entity.Autor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class AutorDTO implements Serializable {

    private int id;

    @NotNull(message = "O campo NOME é requerido")
    private String nome;

    public AutorDTO(Autor obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
