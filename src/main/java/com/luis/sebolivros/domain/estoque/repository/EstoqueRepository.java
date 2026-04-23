package com.luis.sebolivros.domain.estoque.repository;

import com.luis.sebolivros.domain.estoque.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

}
