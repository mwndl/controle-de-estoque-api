package com.datamatch.controle_estoque.exception.custom;

import com.datamatch.controle_estoque.exception.ErrorCode;

public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    // Construtor recebendo o ErrorCode
    public CustomException(ErrorCode errorCode) {
        super(errorCode.getDescription()); // A mensagem da exceção pode ser a descrição do erro
        this.errorCode = errorCode;
    }

    // Método para obter o ErrorCode associado a esta exceção
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
