package com.datamatch.controle_estoque.repository;

import com.datamatch.controle_estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}

