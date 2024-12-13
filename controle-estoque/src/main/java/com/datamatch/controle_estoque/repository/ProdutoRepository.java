package com.datamatch.controle_estoque.repository;

import com.datamatch.controle_estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

