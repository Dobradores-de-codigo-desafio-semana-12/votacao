package com.example.Proposta.web.exception;

import com.example.Proposta.excecao.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(PropostaNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> handlerPropostaNaoEncontradaException(PropostaNaoEncontradoException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PropostaJaDesativadaException.class)
    public ResponseEntity<ErrorMessage> handlePropostaJaDesativadaExceptionException(PropostaJaDesativadaException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FuncionarioNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> handleFuncionarioNaoEncontradoException(FuncionarioNaoEncontradoException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FuncionarioJaVotouException.class)
    public ResponseEntity<ErrorMessage> handleFuncionarioJaVotouException(FuncionarioJaVotouException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropostaJaExisteException.class)
    public ResponseEntity<ErrorMessage> handlePropostaJaExisteExceptionException(PropostaJaExisteException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException e) {
        ErrorMessage errorMessage = new ErrorMessage("Erro interno do servidor", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
