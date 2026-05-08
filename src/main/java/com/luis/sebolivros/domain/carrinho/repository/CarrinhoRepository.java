package com.luis.sebolivros.domain.carrinho.repository;

import com.luis.sebolivros.domain.carrinho.entity.Carrinho;
import com.luis.sebolivros.domain.carrinho.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

    List<Carrinho> findByClienteIdAndStatus(int id, Status status);
}
