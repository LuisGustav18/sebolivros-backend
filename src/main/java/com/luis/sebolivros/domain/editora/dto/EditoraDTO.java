package com.luis.sebolivros.domain.editora.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.editora.entity.Editora;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class EditoraDTO implements Serializable {

    private int id;

    @NotNull(message = "O campo NOME é requerido")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public EditoraDTO(){

    }

    public EditoraDTO(Editora obj) {
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
}
