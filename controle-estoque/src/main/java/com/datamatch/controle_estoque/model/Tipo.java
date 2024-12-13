package com.datamatch.controle_estoque.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "tipo")
    @JsonIgnore  // Adicionando o @JsonIgnore para evitar a recursão
    private List<Subtipo> subtipos;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    @JsonBackReference  // Para evitar a recursão ao serializar Categoria
    private Categoria categoria;

    @Override
    public String toString() {
        // Representação simples, sem a recursão infinita
        return "Tipo{id=" + id + ", nome=" + nome + ", categoria=" + (categoria != null ? categoria.getNome() : "n/d") + "}";
    }
}



