package com.luis.sebolivros.domain.estoque.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.editora.entity.Editora;
import com.luis.sebolivros.domain.estoque.enums.Condicao;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Estoque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double preco;

    private int quantidade;

    private Condicao condicao;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "sebo_id")
    private Sebo sebo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public Estoque(){
    }

    public Estoque(Integer id, Double preco, int quantidade, Condicao condicao, Livro livro, Sebo sebo) {
        this.id = id;
        this.preco = preco;
        this.quantidade = quantidade;
        this.condicao = condicao;
        this.livro = livro;
        this.sebo = sebo;
    }

    public Estoque(Integer id, Double preco, int quantidade, Condicao condicao, String imageUrl, Livro livro, Sebo sebo) {
        this.id = id;
        this.preco = preco;
        this.quantidade = quantidade;
        this.condicao = condicao;
        this.imageUrl = imageUrl;
        this.livro = livro;
        this.sebo = sebo;
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

    public Condicao getCondicao() {
        return condicao;
    }

    public void setCondicao(Condicao condicao) {
        this.condicao = condicao;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Sebo getSebo() {
        return sebo;
    }

    public void setSebo(Sebo sebo) {
        this.sebo = sebo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return Objects.equals(id, estoque.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
