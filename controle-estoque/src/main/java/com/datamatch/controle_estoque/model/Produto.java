package com.datamatch.controle_estoque.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import com.datamatch.controle_estoque.model.Marca;

@Entity
@Data
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String codigoBarras;

    @ManyToOne
    @JoinColumn(name = "id_subtipo")
    @JsonBackReference // Marcamos para evitar a serialização recursiva de 'subtipo'
    private Subtipo subtipo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Override
    public String toString() {
        // Evita recursão infinita e imprime informações chave
        return "Produto{id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", codigoBarras=" + codigoBarras +
                ", subtipo=" + (subtipo != null ? subtipo.getNome() : "n/d") +
                ", marca=" + (marca != null ? marca.getNome() : "n/d") + "}";
    }

}

