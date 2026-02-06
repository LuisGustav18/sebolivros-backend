package com.luis.sebolivros.domain.cliente.repository;

import com.luis.sebolivros.domain.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
