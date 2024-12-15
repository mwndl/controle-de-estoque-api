package com.datamatch.controle_estoque.exception;

import com.datamatch.controle_estoque.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Manipula exceções específicas de CustomException
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        // Criando um ErrorResponse com o código de erro e a descrição do ErrorCode
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getErrorCode().getCode(),
                ex.getErrorCode().getDescription()
        );

        // Usando o status HTTP associado ao ErrorCode
        return new ResponseEntity<>(errorResponse, ex.getErrorCode().getStatus());
    }

    // Manipula exceções genéricas (Exceções não tratadas de forma específica)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        // Para exceções genéricas, você pode retornar um erro interno
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
