package com.luis.sebolivros.domain.sebo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.common.enums.Perfil;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.sebo.dto.SeboDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Sebo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(unique = true)
    private String email;
    private String senha;

    private String cep;

    private Integer perfil;

    @OneToMany(mappedBy = "sebo")
    private List<Livro> livros = new ArrayList<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public Sebo(){
        setPerfil(Perfil.GESTOR);
    }

    public Sebo(Integer id, String nome, String email, String senha, String cep) {
        setPerfil(Perfil.GESTOR);
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
    }

    public Sebo(SeboDTO objDto) {
        setPerfil(Perfil.GESTOR);
        this.id = objDto.getId();
        this.nome = objDto.getNome();
        this.email = objDto.getEmail();
        this.senha = objDto.getSenha();
        this.cep = objDto.getCep();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Perfil getPerfil() {
        return Perfil.toEnum(perfil);
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil.getCodigo();
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sebo sebo = (Sebo) o;
        return id == sebo.id && Objects.equals(email, sebo.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
