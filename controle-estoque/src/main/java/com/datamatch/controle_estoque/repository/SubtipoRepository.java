package com.datamatch.controle_estoque.repository;

import com.datamatch.controle_estoque.model.Subtipo;
import com.datamatch.controle_estoque.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtipoRepository extends JpaRepository<Subtipo, Long> {

    // Verifica se já existe um subtipo generico para o tipo
    boolean existsByTipoAndGenericoTrue(Tipo tipo);

    // Verifica se já existe um subtipo qualquer para o tipo
    boolean existsByTipo(Tipo tipo);
}
