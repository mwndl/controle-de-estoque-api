package com.datamatch.controle_estoque.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "local_armazenamento")
public class LocalArmazenamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;
}
