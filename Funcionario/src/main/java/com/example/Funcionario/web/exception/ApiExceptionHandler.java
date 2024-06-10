package com.example.Funcionario.web.exception;


import com.example.Funcionario.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(FuncionarioNaoSalvoException.class)
    public ResponseEntity<ErrorMessage> handleFuncionarioNaoSalvoException(FuncionarioNaoSalvoException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> handleFuncionarioNaoEncontradoException(FuncionarioNaoEncontradoException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FuncionarioAtualizarException.class)
    public ResponseEntity<ErrorMessage> handleFuncionarioAtualizarException(FuncionarioAtualizarException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FuncionarioDesabilitarException.class)
    public ResponseEntity<ErrorMessage> handleFuncionarioDesabilitarException(FuncionarioDesabilitarException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FuncionarioListaException.class)
    public ResponseEntity<ErrorMessage> handleFuncionarioListaException(FuncionarioListaException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}