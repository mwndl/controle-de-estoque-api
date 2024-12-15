package com.datamatch.controle_estoque.dto;

import lombok.Data;

@Data
public class TipoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private CategoriaResumoDTO categoria;

    @Data
    public static class CategoriaResumoDTO {
        private Long id;
        private String nome;
    }
}
