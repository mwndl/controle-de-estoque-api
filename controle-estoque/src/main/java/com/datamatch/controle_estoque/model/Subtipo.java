package com.datamatch.controle_estoque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
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

    // Método para definir nome e descricao como null caso generico seja true
    @PrePersist
    @PreUpdate
    public void checkGenerico() {
        if (Boolean.TRUE.equals(this.generico)) {
            this.nome = null;
            this.descricao = null;
        }
    }
}
