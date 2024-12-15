package com.datamatch.controle_estoque.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    NOT_FOUND("NOT_FOUND", "Recurso não encontrado", HttpStatus.NOT_FOUND),
    BAD_REQUEST("BAD_REQUEST", "Requisição mal formada", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED("UNAUTHORIZED", "Acesso não autorizado", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("FORBIDDEN", "Acesso proibido", HttpStatus.FORBIDDEN),
    CONFLICT("CONFLICT", "Conflito de dados", HttpStatus.CONFLICT),

    // Erros específicos de Categoria
    CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "Categoria não encontrada", HttpStatus.NOT_FOUND),
    CATEGORY_UPDATE_FAILED("CATEGORY_UPDATE_FAILED", "Falha ao atualizar a categoria", HttpStatus.BAD_REQUEST),
    CATEGORY_DELETE_FAILED("CATEGORY_DELETE_FAILED", "Falha ao excluir a categoria", HttpStatus.BAD_REQUEST),

    // Erros específicos de Tipo
    TYPE_NOT_FOUND("TYPE_NOT_FOUND", "Tipo não encontrado", HttpStatus.NOT_FOUND),
    TYPE_UPDATE_FAILED("TYPE_UPDATE_FAILED", "Falha ao atualizar o tipo", HttpStatus.BAD_REQUEST),
    TYPE_DELETE_FAILED("TYPE_DELETE_FAILED", "Falha ao excluir o tipo", HttpStatus.BAD_REQUEST),
    INVALID_CATEGORY("INVALID_CATEGORY", "Categoria inválida ou não encontrada", HttpStatus.BAD_REQUEST),

    // Erros específicos de Subtipo
    SUBTYPE_NOT_FOUND("SUBTYPE_NOT_FOUND", "Subtipo não encontrado", HttpStatus.NOT_FOUND),
    SUBTYPE_UPDATE_FAILED("SUBTYPE_UPDATE_FAILED", "Falha ao atualizar o subtipo", HttpStatus.BAD_REQUEST),
    SUBTYPE_DELETE_FAILED("SUBTYPE_DELETE_FAILED", "Falha ao excluir o subtipo", HttpStatus.BAD_REQUEST),
    SUBTYPE_GENERIC_ALREADY_EXISTS("SUBTYPE_GENERIC_ALREADY_EXISTS", "Já existe um subtipo genérico para esse tipo", HttpStatus.BAD_REQUEST),
    INVALID_TYPE_ASSOCIATION("INVALID_TYPE_ASSOCIATION", "O tipo associado não existe", HttpStatus.BAD_REQUEST),

    // Erros específicos de Produto
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", "Produto não encontrado", HttpStatus.NOT_FOUND),
    PRODUCT_SAVE_FAILED("PRODUCT_SAVE_FAILED", "Falha ao salvar o produto", HttpStatus.BAD_REQUEST),
    INVALID_SUBTYPE_ASSOCIATION("INVALID_SUBTYPE_ASSOCIATION", "Subtipo inválido ou não encontrado", HttpStatus.BAD_REQUEST),
    INVALID_BRAND_ASSOCIATION("INVALID_BRAND_ASSOCIATION", "Marca inválida ou não encontrada", HttpStatus.BAD_REQUEST);

    // Add novos códigos de erro aqui



    private final String code;
    private final String description;
    private final HttpStatus status;

    // Construtor do enum agora inclui o código de status HTTP
    ErrorCode(String code, String description, HttpStatus status) {
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
