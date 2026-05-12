package com.luis.sebolivros.domain.cliente.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luis.sebolivros.domain.carrinho.entity.Carrinho;
import com.luis.sebolivros.domain.cliente.dto.ClienteDTO;
import com.luis.sebolivros.domain.common.enums.Perfil;
import com.luis.sebolivros.domain.usuario.entity.Usuario;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Usuario {

    @Column(unique = true)
    private String cpf;

    // cascade -> Permite adicionar um carrinho sem id do cliente ainda salvo
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Carrinho> carrinhos = new ArrayList<>();

    public Cliente(){
        super();
        setPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String email, String senha, String cpf) {
        super(senha, email, nome, id);
        this.cpf = cpf;
        setPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO objDto) {
        setPerfil(Perfil.CLIENTE);
        this.id = objDto.getId();
        this.nome = objDto.getNome();
        this.email = objDto.getEmail();
        this.senha = objDto.getSenha();
        this.cpf = objDto.getCpf();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Carrinho> getCarrinhos() {
        return carrinhos;
    }

}
