package com.luis.sebolivros.domain.sebo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.common.enums.Perfil;
import com.luis.sebolivros.domain.sebo.entity.Sebo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class SeboDTO implements Serializable {

    private Integer id;

    @NotNull(message = "O campo NOME é requerido")
    private String nome;

    @NotNull(message = "O campo EMAIL é requerido")
    private String email;

    @NotNull(message = "O campo SENHA é requerido")
    private String senha;

    @NotNull(message = "O campo CEP é requerido")
    private String cep;

    @NotNull(message = "O campo CNPJ é requerido")
    private String cnpj;

    private Integer perfil;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public SeboDTO() {
        setPerfil(Perfil.GESTOR);
    }

    public SeboDTO(Integer id, String nome, String email, String senha, String cep, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
        this.cnpj = cnpj;
        setPerfil(Perfil.GESTOR);
    }

    public SeboDTO(Sebo obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.cep = obj.getCep();
        this.cnpj = obj.getCnpj();
        setPerfil(Perfil.GESTOR);
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
}
