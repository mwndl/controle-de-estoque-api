package com.datamatch.controle_estoque.repository;

import com.datamatch.controle_estoque.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Long> {
}

