package com.luis.sebolivros.domain.livro.repository;

import com.luis.sebolivros.domain.livro.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

    Optional<Livro> findByIdAndSeboId(Integer id, Integer SeboId);
}
