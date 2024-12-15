package com.datamatch.controle_estoque.service;

import com.datamatch.controle_estoque.model.Produto;
import com.datamatch.controle_estoque.repository.ProdutoRepository;
import com.datamatch.controle_estoque.repository.SubtipoRepository;
import com.datamatch.controle_estoque.repository.MarcaRepository;
import com.datamatch.controle_estoque.exception.custom.CustomException;
import com.datamatch.controle_estoque.exception.ErrorCode;
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
        return produtoRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND)); // Produto não encontrado
    }

    // Método para salvar um produto
    public Produto salvarProduto(Produto produto) {
        // Verifica se o subtipo é válido
        if (produto.getSubtipo() == null || produto.getSubtipo().getId() == null || subtipoRepository.findById(produto.getSubtipo().getId()).isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_SUBTYPE_ASSOCIATION); // Subtipo inválido ou não encontrado
        }

        // Verifica se a marca é válida
        if (produto.getMarca() == null || produto.getMarca().getId() == null || marcaRepository.findById(produto.getMarca().getId()).isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_BRAND_ASSOCIATION); // Marca inválida ou não encontrada
        }

        return produtoRepository.save(produto);
    }

    // Método para atualizar um produto
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND)); // Produto não encontrado

        // Atualiza as informações do produto
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setCodigoBarras(produtoAtualizado.getCodigoBarras());
        produtoExistente.setSubtipo(produtoAtualizado.getSubtipo());
        produtoExistente.setMarca(produtoAtualizado.getMarca());

        return produtoRepository.save(produtoExistente);
    }

    // Método para excluir um produto
    public void excluirProduto(Long id) {
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND)); // Produto não encontrado
        produtoRepository.deleteById(id);
    }
}
