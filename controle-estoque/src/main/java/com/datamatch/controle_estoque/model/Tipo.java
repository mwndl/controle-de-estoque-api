package com.datamatch.controle_estoque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "tipo")
    @JsonIgnore
    private List<Subtipo> subtipos;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

}
