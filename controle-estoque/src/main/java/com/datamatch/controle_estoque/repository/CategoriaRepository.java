package com.datamatch.controle_estoque.repository;

import com.datamatch.controle_estoque.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}


