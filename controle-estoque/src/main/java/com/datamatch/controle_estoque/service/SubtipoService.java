package com.datamatch.controle_estoque.service;

import com.datamatch.controle_estoque.model.Subtipo;
import com.datamatch.controle_estoque.model.Tipo;
import com.datamatch.controle_estoque.repository.SubtipoRepository;
import com.datamatch.controle_estoque.exception.custom.CustomException;
import com.datamatch.controle_estoque.exception.ErrorCode;
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
        Tipo tipo = tipoService.buscarTipoPorId(subtipo.getTipo().getId());
        if (tipo == null) {
            throw new CustomException(ErrorCode.INVALID_TYPE_ASSOCIATION); // Erro de tipo inválido
        }

        if (Boolean.TRUE.equals(subtipo.getGenerico())) {
            boolean existeGenerico = subtipoRepository.existsByTipoAndGenericoTrue(subtipo.getTipo());
            if (existeGenerico) {
                throw new CustomException(ErrorCode.SUBTYPE_GENERIC_ALREADY_EXISTS); // Já existe um subtipo genérico
            }
            subtipo.setNome(tipo.getNome());
            subtipo.setDescricao(null);
        }

        return subtipoRepository.save(subtipo);
    }

    // Método para buscar subtipo por ID
    public Subtipo buscarSubtipoPorId(Long id) {
        return subtipoRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.SUBTYPE_NOT_FOUND)); // Erro de subtipo não encontrado
    }

    // Método para atualizar um subtipo existente
    public Subtipo atualizarSubtipo(Long id, Subtipo subtipoAtualizado) {
        Subtipo subtipoExistente = subtipoRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.SUBTYPE_NOT_FOUND)); // Erro de subtipo não encontrado

        Tipo tipo = tipoService.buscarTipoPorId(subtipoAtualizado.getTipo().getId());
        if (tipo == null) {
            throw new CustomException(ErrorCode.INVALID_TYPE_ASSOCIATION); // Erro de tipo inválido
        }

        subtipoExistente.setNome(subtipoAtualizado.getNome());
        subtipoExistente.setDescricao(subtipoAtualizado.getDescricao());
        subtipoExistente.setTipo(tipo);
        subtipoExistente.setGenerico(subtipoAtualizado.getGenerico());

        return subtipoRepository.save(subtipoExistente);
    }

    // Método para excluir um subtipo
    public void excluirSubtipo(Long id) {
        if (!subtipoRepository.existsById(id)) {
            throw new CustomException(ErrorCode.SUBTYPE_NOT_FOUND); // Erro de subtipo não encontrado
        }
        subtipoRepository.deleteById(id);
    }
}
