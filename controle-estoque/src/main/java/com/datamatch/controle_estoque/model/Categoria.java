package com.datamatch.controle_estoque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Tipo> tipos;

    // Getters and Setters (podem ser omitidos com Lombok, se preferir)
}
