package com.luis.sebolivros.domain.carrinho.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luis.sebolivros.domain.carrinho.enums.Status;
import com.luis.sebolivros.domain.cliente.entity.Cliente;
import com.luis.sebolivros.domain.itemCarrinho.entity.ItemCarrinho;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Carrinho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "carrinho")
    private List<ItemCarrinho> itens = new ArrayList<>();

    private Status status;

    private Double subTotal;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();

    public Carrinho(){
        this.status = Status.ATIVO;
    }

    public Carrinho(Cliente cliente) {
        this.status = Status.ATIVO;
        this.cliente = cliente;
    }

    @PrePersist
    @PreUpdate
    public void atualizarSubTotal(){
        if (!itens.isEmpty()){
            double valor = 0;
            for(ItemCarrinho x : itens){
                valor += x.valorProduto();
            }
            this.subTotal = valor;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemCarrinho> getItens(){
        return this.itens;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Carrinho carrinho = (Carrinho) o;
        return Objects.equals(id, carrinho.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
