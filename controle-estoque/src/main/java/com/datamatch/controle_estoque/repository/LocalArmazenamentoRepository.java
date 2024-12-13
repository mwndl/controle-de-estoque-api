package com.datamatch.controle_estoque.repository;

import com.datamatch.controle_estoque.model.LocalArmazenamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalArmazenamentoRepository extends JpaRepository<LocalArmazenamento, Long> {
}

