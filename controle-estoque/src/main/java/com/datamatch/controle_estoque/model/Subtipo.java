package com.datamatch.controle_estoque.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "subtipo")
public class Subtipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;

    @OneToMany(mappedBy = "subtipo")
    @JsonIgnore
    private List<Produto> produtos;

    private Boolean generico = false;

    // Método que será chamado antes de realizar o update
    @PreUpdate
    public void preUpdate() {
        // Se for necessário, limpe nome e descricao antes de atualizar no banco
        if (this.generico != null && this.generico) {
            this.nome = null;
            this.descricao = null;
        }
    }

    @Override
    public String toString() {
        // Representação simples, sem a recursão infinita
        return "Subtipo{id=" + id + ", nome=" + nome + ", tipo=" + (tipo != null ? tipo.getNome() : "n/d") + ", generico=" + generico + "}";
    }

}



