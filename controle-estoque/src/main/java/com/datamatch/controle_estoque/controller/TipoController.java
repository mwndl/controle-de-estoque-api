package com.datamatch.controle_estoque.controller;

import com.datamatch.controle_estoque.dto.TipoDTO;
import com.datamatch.controle_estoque.dto.TipoResponseDTO;
import com.datamatch.controle_estoque.model.Tipo;
import com.datamatch.controle_estoque.service.TipoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Tipos", description = "Endpoints relacionados aos tipos de produtos (ex.: Frutas, Legumes, Refrigerantes)")
@RestController
@RequestMapping("/api/tipos")
public class TipoController {

    @Autowired
    private TipoService tipoService;

    // Endpoint para listar todos os tipos
    @GetMapping
    public List<TipoResponseDTO> listarTipos() {
        return tipoService.listarTipos()
                .stream()
                .map(tipo -> tipoService.toResponseDTO(tipo))
                .collect(Collectors.toList());
    }

    // Endpoint para salvar um tipo
    @PostMapping
    public TipoResponseDTO salvarTipo(@RequestBody @Valid TipoDTO tipoDTO) {
        Tipo tipo = tipoService.toEntity(tipoDTO);
        Tipo savedTipo = tipoService.salvarTipo(tipo);
        return tipoService.toResponseDTO(savedTipo);
    }

    // Endpoint para buscar tipo por ID
    @GetMapping("/{id}")
    public TipoResponseDTO buscarTipoPorId(@PathVariable Long id) {
        Tipo tipo = tipoService.buscarTipoPorId(id);
        return tipoService.toResponseDTO(tipo);
    }

    // Endpoint para atualizar um tipo
    @PutMapping("/{id}")
    public TipoResponseDTO atualizarTipo(
            @PathVariable Long id,
            @RequestBody @Valid TipoDTO tipoDTO) {
        Tipo tipoAtualizado = tipoService.atualizarTipo(id, tipoService.toEntity(tipoDTO));
        return tipoService.toResponseDTO(tipoAtualizado);
    }

    // Endpoint para excluir um tipo
    @DeleteMapping("/{id}")
    public void excluirTipo(@PathVariable Long id) {
        tipoService.excluirTipo(id);
    }
}
