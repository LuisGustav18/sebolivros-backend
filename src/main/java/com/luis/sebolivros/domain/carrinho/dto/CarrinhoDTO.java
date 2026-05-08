package com.luis.sebolivros.domain.carrinho.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.carrinho.entity.Carrinho;
import com.luis.sebolivros.domain.carrinho.enums.Status;
import com.luis.sebolivros.domain.itemCarrinho.dto.ItemCarrinhoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class CarrinhoDTO implements Serializable {

    private Integer id;

    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer cliente;

    private List<ItemCarrinhoDTO> itens;

    @NotNull(message = "O campo STATUS é requerido")
    private Integer status;

    private Double subTotal;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public CarrinhoDTO(){
    }

    public CarrinhoDTO(Integer cliente){
        this.cliente = cliente;
    }

    public CarrinhoDTO(Carrinho obj){
        this.id = obj.getId();
        this.cliente = obj.getCliente().getId();
        this.itens = obj.getItens().stream().map(ItemCarrinhoDTO::new).toList();
        this.status = obj.getStatus().getCodigo();
        this.subTotal = obj.getSubTotal();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public List<ItemCarrinhoDTO> getItens() {
        return itens;
    }

    public Status getStatus() {
        return Status.toEnum(this.status);
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setStatus(Status status) {
        this.status = status.getCodigo();
    }


    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
