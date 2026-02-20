package com.luis.sebolivros.security;

import com.luis.sebolivros.domain.common.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    private Integer id;

    private String email;

    private String senha;

    private Perfil role;

    public User(Integer id, String email, String senha, Perfil role) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public User(String email, String senha, Perfil role){
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Perfil.GESTOR) return List.of(new SimpleGrantedAuthority(Perfil.GESTOR.getDescricao()), new SimpleGrantedAuthority(Perfil.CLIENTE.getDescricao()));
        return List.of(new SimpleGrantedAuthority(Perfil.CLIENTE.getDescricao()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
