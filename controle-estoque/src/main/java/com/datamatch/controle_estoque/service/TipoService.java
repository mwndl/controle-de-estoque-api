package com.datamatch.controle_estoque.service;

import com.datamatch.controle_estoque.model.Tipo;
import com.datamatch.controle_estoque.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    // Método para listar todos os tipos
    public List<Tipo> listarTipos() {
        return tipoRepository.findAll();
    }

    // Método para buscar tipo por ID
    public Tipo buscarTipoPorId(Long id) {
        Optional<Tipo> tipo = tipoRepository.findById(id);
        return tipo.orElse(null);
    }

    // Método para salvar um tipo
    public Tipo salvarTipo(Tipo tipo) {
        return tipoRepository.save(tipo);
    }
}
