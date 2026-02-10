package com.luis.sebolivros.domain.editora.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.editora.dto.EditoraDTO;
import com.luis.sebolivros.domain.livro.entity.Livro;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Editora implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(mappedBy = "editora")
    private List<Livro> livros = new ArrayList<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public Editora(){

    }

    public Editora(String nome, Integer id) {
        this.nome = nome;
        this.id = id;
    }

    public Editora(EditoraDTO objDto) {
        this.nome = objDto.getNome();
        this.id = objDto.getId();
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

    public List<Livro> getLivros() {
        return livros;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Editora autor = (Editora) o;
        return id == autor.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
