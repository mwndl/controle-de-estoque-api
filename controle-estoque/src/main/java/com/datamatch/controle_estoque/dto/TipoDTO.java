package com.datamatch.controle_estoque.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class TipoDTO {

    @NotBlank(message = "O nome do tipo é obrigatório.")
    private String nome;

    private String descricao;

    @NotNull(message = "O ID da categoria é obrigatório.")
    private Long categoriaId;
}
