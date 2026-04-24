package com.luis.sebolivros.domain.livro.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.editora.entity.Editora;
import com.luis.sebolivros.domain.autor.entity.Autor;
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

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private int anoDeLancamento;

    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;

    @Column(unique = true)
    private String isbn;

    private String imageUrl;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public Livro(){

    }

    public Livro(Integer id, String titulo, Autor autor,
                 int anoDeLancamento, Editora editora, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoDeLancamento = anoDeLancamento;
        this.editora = editora;
        this.isbn = isbn;
    }

    public Livro(Integer id, String titulo, Autor autor, int anoDeLancamento,
                 Editora editora, String isbn, String imageUrl) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoDeLancamento = anoDeLancamento;
        this.editora = editora;
        this.isbn = isbn;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
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
