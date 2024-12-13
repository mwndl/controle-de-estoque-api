package com.datamatch.controle_estoque.controller;

import com.datamatch.controle_estoque.model.Produto;
import com.datamatch.controle_estoque.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produtos", description = "Endpoints relacionados aos produtos (ex.: Coca-Cola Lata 350ml, Creme de Leite Italac Caixa 200g)")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Endpoint para listar todos os produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    // Endpoint para salvar um produto
    @PostMapping
    public Produto salvarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    // Endpoint para buscar um produto por ID
    @GetMapping("/{id}")
    public Produto buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id);
    }
}
