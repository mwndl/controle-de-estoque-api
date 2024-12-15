package com.datamatch.controle_estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoDTO {

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nome;

    private String descricao;

    private String codigoBarras;

    @NotNull(message = "O ID do subtipo é obrigatório.")
    private Long subtipoId;

    @NotNull(message = "O ID da marca é obrigatório.")
    private Long marcaId;
}
