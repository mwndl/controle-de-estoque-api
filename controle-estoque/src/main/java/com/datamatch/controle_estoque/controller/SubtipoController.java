package com.datamatch.controle_estoque.controller;

import com.datamatch.controle_estoque.model.Subtipo;
import com.datamatch.controle_estoque.service.SubtipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
