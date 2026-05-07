package com.luis.sebolivros.domain.itemCarrinho.repository;

import com.luis.sebolivros.domain.itemCarrinho.entity.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Integer> {
}
