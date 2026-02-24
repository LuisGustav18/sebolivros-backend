package com.luis.sebolivros.domain.autor.repository;

import com.luis.sebolivros.domain.autor.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

}
