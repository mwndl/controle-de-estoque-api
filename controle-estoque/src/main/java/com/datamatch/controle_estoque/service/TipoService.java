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

    @Autowired
    private CategoriaService categoriaService;

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
        // Verifica se a categoria associada ao tipo é válida
        if (tipo.getCategoria() == null || categoriaService.buscarCategoriaPorId(tipo.getCategoria().getId()) == null) {
            throw new RuntimeException("Categoria inválida ou não encontrada");
        }

        return tipoRepository.save(tipo);
    }

    // Método para atualizar um tipo
    public Tipo atualizarTipo(Long id, Tipo tipo) {
        if (tipoRepository.existsById(id)) {
            tipo.setId(id);  // Certifique-se de que o tipo tenha o ID correto

            // Verifica se a categoria associada ao tipo é válida
            if (tipo.getCategoria() == null || categoriaService.buscarCategoriaPorId(tipo.getCategoria().getId()) == null) {
                throw new RuntimeException("Categoria inválida ou não encontrada");
            }

            return tipoRepository.save(tipo);
        }
        return null;  // Caso o tipo não seja encontrado, retornamos null
    }

    // Método para excluir um tipo
    public void excluirTipo(Long id) {
        tipoRepository.deleteById(id);
    }
}
