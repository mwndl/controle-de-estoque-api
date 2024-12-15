package com.datamatch.controle_estoque.controller;

import com.datamatch.controle_estoque.dto.ProdutoDTO;
import com.datamatch.controle_estoque.model.Marca;
import com.datamatch.controle_estoque.model.Produto;
import com.datamatch.controle_estoque.model.Subtipo;
import com.datamatch.controle_estoque.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Produtos", description = "Endpoints relacionados aos produtos (ex.: Coca-Cola Lata 350ml, Creme de Leite Italac Caixa 200g)")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Endpoint para listar todos os produtos
    @GetMapping
    public List<ProdutoDTO> listarProdutos() {
        return produtoService.listarProdutos()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Endpoint para salvar um produto
    @PostMapping
    public ProdutoDTO salvarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoService.salvarProduto(toEntity(produtoDTO));
        return toDTO(produto);
    }

    // Endpoint para buscar um produto por ID
    @GetMapping("/{id}")
    public ProdutoDTO buscarProdutoPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarProdutoPorId(id);
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado.");
        }
        return toDTO(produto);
    }

    // Converte entidade para DTO
    private ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setCodigoBarras(produto.getCodigoBarras());
        dto.setSubtipoId(produto.getSubtipo().getId());
        dto.setMarcaId(produto.getMarca().getId());
        return dto;
    }

    // Converte DTO para entidade
    private Produto toEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setCodigoBarras(produtoDTO.getCodigoBarras());
        produto.setSubtipo(new Subtipo());
        produto.getSubtipo().setId(produtoDTO.getSubtipoId());
        produto.setMarca(new Marca());
        produto.getMarca().setId(produtoDTO.getMarcaId());
        return produto;
    }
}
