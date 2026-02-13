package com.luis.sebolivros.domain.cliente.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.cliente.dto.ClienteDTO;
import com.luis.sebolivros.domain.common.enums.Perfil;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Column(unique = true)
    private String email;
    private String senha;

    private Integer perfil;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public Cliente(){
        setPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String email, String senha) {
        setPerfil(Perfil.CLIENTE);
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Cliente(ClienteDTO objDto) {
        setPerfil(Perfil.CLIENTE);
        this.id = objDto.getId();
        this.nome = objDto.getNome();
        this.email = objDto.getEmail();
        this.senha = objDto.getSenha();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id == cliente.id && Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
