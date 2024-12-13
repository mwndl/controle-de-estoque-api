package com.datamatch.controle_estoque.controller;

import com.datamatch.controle_estoque.model.Tipo;
import com.datamatch.controle_estoque.service.TipoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Tipos", description = "Endpoints relacionados aos tipos de produtos (ex.: Frutas, Legumes, Refrigerantes)")
@RestController
@RequestMapping("/api/tipos")
public class TipoController {

    @Autowired
    private TipoService tipoService;

    // Endpoint para listar todos os tipos
    @GetMapping
    public List<Tipo> listarTipos() {
        return tipoService.listarTipos();
    }

    // Endpoint para salvar um tipo
    @PostMapping
    public Tipo salvarTipo(@RequestBody Tipo tipo) {
        return tipoService.salvarTipo(tipo);
    }

    // Endpoint para buscar tipo por ID
    @GetMapping("/{id}")
    public Tipo buscarTipoPorId(@PathVariable Long id) {
        return tipoService.buscarTipoPorId(id);
    }

    // Endpoint para atualizar um tipo
    @PutMapping("/{id}")
    public Tipo atualizarTipo(@PathVariable Long id, @RequestBody Tipo tipo) {
        return tipoService.atualizarTipo(id, tipo);
    }

    // Endpoint para excluir um tipo
    @DeleteMapping("/{id}")
    public void excluirTipo(@PathVariable Long id) {
        tipoService.excluirTipo(id);
    }
}
