package com.datamatch.controle_estoque.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}

