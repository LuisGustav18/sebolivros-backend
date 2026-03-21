package com.luis.sebolivros.domain.livro.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.editora.entity.Editora;
import com.luis.sebolivros.domain.autor.entity.Autor;
import com.luis.sebolivros.domain.livro.dto.LivroDTO;
import com.luis.sebolivros.domain.livro.enums.Condicao;
import com.luis.sebolivros.domain.livro.enums.Estado;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private int anoDeLancamento;

    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;

    private String isbn;

    private Condicao condicao;

    private Estado estado;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "sebo_id")
    private Sebo sebo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public Livro(){

    }

    public Livro(Integer id, String titulo, int quantidade,
                 Autor autor, int anoDeLancamento, Editora editora, String isbn,
                 Condicao condicao, Sebo sebo) {
        this.id = id;
        this.titulo = titulo;
        this.quantidade = quantidade;
        this.autor = autor;
        this.anoDeLancamento = anoDeLancamento;
        this.editora = editora;
        this.isbn = isbn;
        this.condicao = condicao;
        this.sebo = sebo;
    }

    public Livro(Integer id, String titulo, int quantidade,
                 Autor autor, int anoDeLancamento, Editora editora, String isbn,
                 Condicao condicao, String imageUrl, Sebo sebo) {
        this.id = id;
        this.titulo = titulo;
        this.quantidade = quantidade;
        this.autor = autor;
        this.anoDeLancamento = anoDeLancamento;
        this.editora = editora;
        this.isbn = isbn;
        this.condicao = condicao;
        this.imageUrl = imageUrl;
        this.sebo = sebo;
    }

    @PrePersist
    @PreUpdate
    public void atualizarEstado(){
        this.estado = this.quantidade > 0 ?
                Estado.DISPONIVEL :
                Estado.INDISPONIVEL;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Condicao getCondicao() {
        return condicao;
    }

    public void setCondicao(Condicao condicao) {
        this.condicao = condicao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    public Sebo getSebo() {
        return sebo;
    }

    public void setSebo(Sebo sebo) {
        this.sebo = sebo;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return id == livro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
