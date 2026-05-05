package com.luis.sebolivros.domain.itemCarrinho.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.carrinho.entity.Carrinho;
import com.luis.sebolivros.domain.estoque.entity.Estoque;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ItemCarrinhoDTO implements Serializable {

    private Integer id;

    @NotNull(message = "O campo CARRINHO é requerido")
    private Integer carrinho;

    @NotNull(message = "O campo ESTOUQ é requerido")
    private Integer estoque;

    @NotNull(message = "O campo QUANTIDADE é requerido")
    private Integer quantidade;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public ItemCarrinhoDTO(){

    }

    public ItemCarrinhoDTO(Integer carrinho, Integer estoque, Integer quantidade) {
        this.carrinho = carrinho;
        this.estoque = estoque;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Integer carrinho) {
        this.carrinho = carrinho;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
