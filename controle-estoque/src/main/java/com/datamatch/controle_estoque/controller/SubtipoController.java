package com.datamatch.controle_estoque.controller;

import com.datamatch.controle_estoque.dto.SubtipoDTO;
import com.datamatch.controle_estoque.model.Subtipo;
import com.datamatch.controle_estoque.model.Tipo;
import com.datamatch.controle_estoque.service.SubtipoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Subtipos", description = "Endpoints relacionados aos subtipos de produtos (ex.: Cerveja, Refrigerante, Maçã)")
@RestController
@RequestMapping("/api/subtipos")
public class SubtipoController {

    @Autowired
    private SubtipoService subtipoService;

    // Endpoint para listar todos os subtipos
    @GetMapping
    public List<SubtipoDTO> listarSubtipos() {
        return subtipoService.listarSubtipos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Endpoint para salvar um subtipo
    @PostMapping
    public SubtipoDTO salvarSubtipo(@Valid @RequestBody SubtipoDTO subtipoDTO) {
        Subtipo subtipo = subtipoService.salvarSubtipo(toEntity(subtipoDTO));
        return toDTO(subtipo);
    }

    // Endpoint para buscar subtipo por ID
    @GetMapping("/{id}")
    public SubtipoDTO buscarSubtipoPorId(@PathVariable Long id) {
        Subtipo subtipo = subtipoService.buscarSubtipoPorId(id);
        if (subtipo == null) {
            throw new RuntimeException("Subtipo não encontrado.");
        }
        return toDTO(subtipo);
    }

    // Endpoint para atualizar um subtipo existente
    @PutMapping("/{id}")
    public SubtipoDTO atualizarSubtipo(@PathVariable Long id, @Valid @RequestBody SubtipoDTO subtipoDTO) {
        Subtipo subtipoAtualizado = subtipoService.atualizarSubtipo(id, toEntity(subtipoDTO));
        return toDTO(subtipoAtualizado);
    }

    // Endpoint para excluir um subtipo
    @DeleteMapping("/{id}")
    public void excluirSubtipo(@PathVariable Long id) {
        subtipoService.excluirSubtipo(id);
    }

    // Converte entidade para DTO
    private SubtipoDTO toDTO(Subtipo subtipo) {
        SubtipoDTO dto = new SubtipoDTO();
        dto.setNome(subtipo.getNome());
        dto.setDescricao(subtipo.getDescricao());
        dto.setTipoId(subtipo.getTipo().getId());
        dto.setGenerico(subtipo.getGenerico());
        return dto;
    }

    // Converte DTO para entidade
    private Subtipo toEntity(SubtipoDTO subtipoDTO) {
        Subtipo subtipo = new Subtipo();
        subtipo.setNome(subtipoDTO.getNome());
        subtipo.setDescricao(subtipoDTO.getDescricao());
        subtipo.setTipo(new Tipo());
        subtipo.getTipo().setId(subtipoDTO.getTipoId());
        subtipo.setGenerico(subtipoDTO.getGenerico());
        return subtipo;
    }
}
