package com.datamatch.controle_estoque.controller;

import com.datamatch.controle_estoque.model.Tipo;
import com.datamatch.controle_estoque.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
