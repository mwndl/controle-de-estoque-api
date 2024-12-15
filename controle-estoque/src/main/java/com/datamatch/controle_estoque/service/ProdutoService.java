package com.datamatch.controle_estoque.service;

import com.datamatch.controle_estoque.model.Produto;
import com.datamatch.controle_estoque.repository.ProdutoRepository;
import com.datamatch.controle_estoque.repository.SubtipoRepository;
import com.datamatch.controle_estoque.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private SubtipoRepository subtipoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    // Método para listar todos os produtos
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Método para buscar produto por ID
    public Produto buscarProdutoPorId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElse(null);
    }

    // Método para salvar um produto
    public Produto salvarProduto(Produto produto) {
        // Verifica se o subtipo é válido
        if (produto.getSubtipo() == null || produto.getSubtipo().getId() == null || subtipoRepository.findById(produto.getSubtipo().getId()).isEmpty()) {
            throw new RuntimeException("Subtipo inválido ou não encontrado");
        }

        // Verifica se a marca é válida
        if (produto.getMarca() == null || produto.getMarca().getId() == null || marcaRepository.findById(produto.getMarca().getId()).isEmpty()) {
            throw new RuntimeException("Marca inválida ou não encontrada");
        }

        return produtoRepository.save(produto);
    }

    // Método para atualizar um produto (se necessário)
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isEmpty()) {
            throw new RuntimeException("Produto não encontrado");
        }

        Produto produto = produtoOptional.get();

        // Atualiza as informações do produto
        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setCodigoBarras(produtoAtualizado.getCodigoBarras());
        produto.setSubtipo(produtoAtualizado.getSubtipo()); // Verifica se o subtipo também é válido
        produto.setMarca(produtoAtualizado.getMarca());

        return produtoRepository.save(produto);
    }

    // Método para excluir um produto
    public void excluirProduto(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isEmpty()) {
            throw new RuntimeException("Produto não encontrado");
        }

        produtoRepository.deleteById(id);
    }
}
