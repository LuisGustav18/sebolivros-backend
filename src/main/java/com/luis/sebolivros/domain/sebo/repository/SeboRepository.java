package com.luis.sebolivros.domain.sebo.repository;

import com.luis.sebolivros.domain.sebo.entity.Sebo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeboRepository extends JpaRepository<Sebo, Integer> {

    Optional<Sebo> findByEmail(String email);
}
