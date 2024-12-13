package com.datamatch.controle_estoque.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private int quantidade;

    private Date dataValidade;

    @ManyToOne
    @JoinColumn(name = "id_local")
    private LocalArmazenamento local;
}

