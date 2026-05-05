package com.luis.sebolivros.domain.carrinho.repository;

import com.luis.sebolivros.domain.carrinho.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Integer, Carrinho> {
}
