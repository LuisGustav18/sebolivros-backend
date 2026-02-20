package com.luis.sebolivros.domain.cliente.entity;

import com.luis.sebolivros.domain.cliente.dto.ClienteDTO;
import com.luis.sebolivros.domain.common.enums.Perfil;
import com.luis.sebolivros.domain.usuario.entity.Usuario;
import jakarta.persistence.*;

@Entity
public class Cliente extends Usuario {

    public Cliente(){
        super();
        setPerfil(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String email, String senha) {
        super(senha, email, nome, id);
        setPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO objDto) {
        setPerfil(Perfil.CLIENTE);
        this.id = objDto.getId();
        this.nome = objDto.getNome();
        this.email = objDto.getEmail();
        this.senha = objDto.getSenha();
    }


}
