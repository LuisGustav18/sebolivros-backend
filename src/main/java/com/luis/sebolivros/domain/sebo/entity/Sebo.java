package com.luis.sebolivros.domain.sebo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luis.sebolivros.domain.common.enums.Perfil;
import com.luis.sebolivros.domain.livro.entity.Livro;
import com.luis.sebolivros.domain.sebo.dto.SeboDTO;
import com.luis.sebolivros.domain.usuario.entity.Usuario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Sebo extends Usuario {

    private String cep;

    @OneToMany(mappedBy = "sebo")
    @JsonIgnore
    private List<Livro> livros = new ArrayList<>();

    public Sebo(){
        super();
        setPerfil(Perfil.GESTOR);
    }

    public Sebo(Integer id, String nome, String email, String senha, String cep) {
        super(senha, email, nome, id);
        this.cep = cep;
        setPerfil(Perfil.GESTOR);
    }

    public Sebo(SeboDTO objDto) {
        setPerfil(Perfil.GESTOR);
        this.id = objDto.getId();
        this.nome = objDto.getNome();
        this.email = objDto.getEmail();
        this.senha = objDto.getSenha();
        this.cep = objDto.getCep();
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
