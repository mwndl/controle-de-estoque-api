package com.datamatch.controle_estoque.service;

import com.datamatch.controle_estoque.model.Subtipo;
import com.datamatch.controle_estoque.repository.SubtipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtipoService {

    @Autowired
    private SubtipoRepository subtipoRepository;

    @Autowired
    private TipoService tipoService;

    // Método para listar todos os subtipos
    public List<Subtipo> listarSubtipos() {
        return subtipoRepository.findAll();
    }

    // Método para salvar um subtipo, associando-o a um tipo
    public Subtipo salvarSubtipo(Subtipo subtipo) {
        // Verifica se o tipo existe
        if (subtipo.getTipo() == null || tipoService.buscarTipoPorId(subtipo.getTipo().getId()) == null) {
            throw new RuntimeException("Tipo inválido ou não encontrado");
        }

        return subtipoRepository.save(subtipo);
    }

    // Método para buscar subtipo por ID
    public Subtipo buscarSubtipoPorId(Long id) {
        return subtipoRepository.findById(id).orElse(null);
    }
}
