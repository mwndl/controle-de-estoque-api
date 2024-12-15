package com.datamatch.controle_estoque.service;

import com.datamatch.controle_estoque.dto.TipoDTO;
import com.datamatch.controle_estoque.dto.TipoResponseDTO;
import com.datamatch.controle_estoque.exception.custom.CustomException;
import com.datamatch.controle_estoque.model.Categoria;
import com.datamatch.controle_estoque.model.Tipo;
import com.datamatch.controle_estoque.repository.TipoRepository;
import com.datamatch.controle_estoque.exception.ErrorCode;
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
        return tipoRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.TYPE_NOT_FOUND)); // Usando erro específico
    }

    // Método para salvar um tipo
    public Tipo salvarTipo(Tipo tipo) {
        validarCategoria(tipo.getCategoria());
        return tipoRepository.save(tipo);
    }

    // Método para atualizar um tipo
    public Tipo atualizarTipo(Long id, Tipo tipo) {
        if (!tipoRepository.existsById(id)) {
            throw new CustomException(ErrorCode.TYPE_NOT_FOUND); // Erro específico
        }
        validarCategoria(tipo.getCategoria());
        tipo.setId(id); // Certifique-se de que o ID está correto
        return tipoRepository.save(tipo);
    }

    // Método para excluir um tipo
    public void excluirTipo(Long id) {
        if (!tipoRepository.existsById(id)) {
            throw new CustomException(ErrorCode.TYPE_NOT_FOUND); // Erro específico
        }
        tipoRepository.deleteById(id);
    }

    // Valida se a categoria existe
    private void validarCategoria(Categoria categoria) {
        if (categoria == null || categoriaService.buscarCategoriaPorId(categoria.getId()) == null) {
            throw new CustomException(ErrorCode.INVALID_CATEGORY); // Erro específico para categoria inválida
        }
    }

    // Conversão de Tipo para TipoResponseDTO
    public TipoResponseDTO toResponseDTO(Tipo tipo) {
        TipoResponseDTO dto = new TipoResponseDTO();
        dto.setId(tipo.getId());
        dto.setNome(tipo.getNome());
        dto.setDescricao(tipo.getDescricao());

        TipoResponseDTO.CategoriaResumoDTO categoriaResumoDTO = new TipoResponseDTO.CategoriaResumoDTO();
        categoriaResumoDTO.setId(tipo.getCategoria().getId());
        categoriaResumoDTO.setNome(tipo.getCategoria().getNome());
        dto.setCategoria(categoriaResumoDTO);

        return dto;
    }

    // Conversão de TipoDTO para Tipo
    public Tipo toEntity(TipoDTO tipoDTO) {
        Tipo tipo = new Tipo();
        tipo.setNome(tipoDTO.getNome());
        tipo.setDescricao(tipoDTO.getDescricao());

        Categoria categoria = categoriaService.buscarCategoriaPorId(tipoDTO.getCategoriaId());
        tipo.setCategoria(categoria);

        return tipo;
    }
}
