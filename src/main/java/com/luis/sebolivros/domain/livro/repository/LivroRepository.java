package com.luis.sebolivros.domain.livro.repository;

import com.luis.sebolivros.domain.livro.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
