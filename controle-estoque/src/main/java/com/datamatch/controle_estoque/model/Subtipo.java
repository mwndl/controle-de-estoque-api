package com.datamatch.controle_estoque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
public class Subtipo {

    @Getter
    @Setter
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

}
