package com.datamatch.controle_estoque.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubtipoDTO {

    @NotBlank(message = "O nome do subtipo é obrigatório.")
    private String nome;

    private String descricao;

    @NotNull(message = "O ID do tipo é obrigatório.")
    private Long tipoId;

    private Boolean generico = false;
}
