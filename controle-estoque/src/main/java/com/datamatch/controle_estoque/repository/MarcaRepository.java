package com.datamatch.controle_estoque.repository;

import com.datamatch.controle_estoque.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}

