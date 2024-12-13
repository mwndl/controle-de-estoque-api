package com.datamatch.controle_estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import com.datamatch.controle_estoque.model.Marca;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String codigoBarras;

    @ManyToOne
    @JoinColumn(name = "id_subtipo")
    private Subtipo subtipo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
}
