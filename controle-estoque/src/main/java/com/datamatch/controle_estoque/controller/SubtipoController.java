// CONTROLLER:
package com.datamatch.controle_estoque.controller;

import com.datamatch.controle_estoque.model.Subtipo;
import com.datamatch.controle_estoque.service.SubtipoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Subtipos", description = "Endpoints relacionados aos subtipos de produtos (ex.: Cerveja, Refrigerante, Maçã)")
@RestController
@RequestMapping("/api/subtipos")
public class SubtipoController {

    @Autowired
    private SubtipoService subtipoService;

    // Endpoint para listar todos os subtipos
    @GetMapping
    public List<Subtipo> listarSubtipos() {
        return subtipoService.listarSubtipos();
    }

    // Endpoint para salvar um subtipo (associando ao tipo)
    @PostMapping
    public Subtipo salvarSubtipo(@RequestBody Subtipo subtipo) {
        return subtipoService.salvarSubtipo(subtipo);
    }

    // Endpoint para buscar subtipo por ID
    @GetMapping("/{id}")
    public Subtipo buscarSubtipoPorId(@PathVariable Long id) {
        return subtipoService.buscarSubtipoPorId(id);
    }

    // Endpoint para atualizar um subtipo existente
    @PutMapping("/{id}")
    public Subtipo atualizarSubtipo(@PathVariable Long id, @RequestBody Subtipo subtipoAtualizado) {
        return subtipoService.atualizarSubtipo(id, subtipoAtualizado);
    }

    // Endpoint para excluir um subtipo
    @DeleteMapping("/{id}")
    public void excluirSubtipo(@PathVariable Long id) {
        subtipoService.excluirSubtipo(id);
    }
}