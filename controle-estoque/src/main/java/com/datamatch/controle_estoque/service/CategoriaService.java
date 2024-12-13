package com.datamatch.controle_estoque.service;

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
        return categoria.orElse(null);
    }

    // Método para salvar uma categoria
    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Método para atualizar uma categoria
    public Categoria atualizarCategoria(Long id, Categoria categoria) {
        if (categoriaRepository.existsById(id)) {
            categoria.setId(id);  // Certifique-se de que a categoria tenha o ID correto
            return categoriaRepository.save(categoria);
        }
        return null;  // Caso a categoria não seja encontrada, retornamos null
    }

    // Método para excluir uma categoria
    public void excluirCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
