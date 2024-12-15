package com.datamatch.controle_estoque.service;

import com.datamatch.controle_estoque.dto.CategoriaDTO;
import com.datamatch.controle_estoque.dto.CategoriaResponseDTO;
import com.datamatch.controle_estoque.model.Categoria;
import com.datamatch.controle_estoque.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Método para listar todas as categorias
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Método para buscar categoria por ID
    public Categoria buscarCategoriaPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
    }

    // Método para salvar uma categoria
    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Método para atualizar uma categoria
    public Categoria atualizarCategoria(Long id, Categoria categoria) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada para atualização.");
        }
        categoria.setId(id); // Certifique-se de que o ID está correto
        return categoriaRepository.save(categoria);
    }

    // Método para excluir uma categoria
    public void excluirCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada para exclusão.");
        }
        categoriaRepository.deleteById(id);
    }

    // Conversão de Categoria para CategoriaResponseDTO
    public CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        CategoriaResponseDTO dto = new CategoriaResponseDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        dto.setDescricao(categoria.getDescricao());
        return dto;
    }

    // Conversão de CategoriaDTO para Categoria
    public Categoria toEntity(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());
        return categoria;
    }
}
