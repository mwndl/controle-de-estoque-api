package com.datamatch.controle_estoque.controller;

import com.datamatch.controle_estoque.model.Categoria;
import com.datamatch.controle_estoque.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categorias", description = "Endpoints relacionados às categorias de produtos (ex.: Padaria, Bebidas, Hortifruti)")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Endpoint para listar todas as categorias
    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    // Endpoint para salvar uma categoria
    @PostMapping
    public Categoria salvarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.salvarCategoria(categoria);
    }

    // Endpoint para buscar categoria por ID
    @GetMapping("/{id}")
    public Categoria buscarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id);
    }

    // Endpoint para atualizar uma categoria
    @PutMapping("/{id}")
    public Categoria atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        return categoriaService.atualizarCategoria(id, categoria);
    }

    // Endpoint para excluir uma categoria
    @DeleteMapping("/{id}")
    public void excluirCategoria(@PathVariable Long id) {
        categoriaService.excluirCategoria(id);
    }
}
