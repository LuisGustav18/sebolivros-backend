package com.luis.sebolivros.domain.sebo.repository;

import com.luis.sebolivros.domain.autor.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeboRepository extends JpaRepository<Autor, Integer> {

}
