package com.datamatch.controle_estoque.controller;

import com.datamatch.controle_estoque.dto.CategoriaDTO;
import com.datamatch.controle_estoque.dto.CategoriaResponseDTO;
import com.datamatch.controle_estoque.model.Categoria;
import com.datamatch.controle_estoque.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Categorias", description = "Endpoints relacionados às categorias de produtos (ex.: Padaria, Bebidas, Hortifruti)")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Endpoint para listar todas as categorias
    @GetMapping
    public List<CategoriaResponseDTO> listarCategorias() {
        return categoriaService.listarCategorias()
                .stream()
                .map(categoria -> categoriaService.toResponseDTO(categoria))
                .collect(Collectors.toList());
    }

    // Endpoint para salvar uma categoria
    @PostMapping
    public CategoriaResponseDTO salvarCategoria(@RequestBody @Valid CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaService.toEntity(categoriaDTO);
        Categoria savedCategoria = categoriaService.salvarCategoria(categoria);
        return categoriaService.toResponseDTO(savedCategoria);
    }

    // Endpoint para buscar categoria por ID
    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.buscarCategoriaPorId(id);
        return categoriaService.toResponseDTO(categoria);
    }

    // Endpoint para atualizar uma categoria
    @PutMapping("/{id}")
    public CategoriaResponseDTO atualizarCategoria(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaDTO categoriaDTO) {
        Categoria categoriaAtualizada = categoriaService.atualizarCategoria(id, categoriaService.toEntity(categoriaDTO));
        return categoriaService.toResponseDTO(categoriaAtualizada);
    }

    // Endpoint para excluir uma categoria
    @DeleteMapping("/{id}")
    public void excluirCategoria(@PathVariable Long id) {
        categoriaService.excluirCategoria(id);
    }
}
