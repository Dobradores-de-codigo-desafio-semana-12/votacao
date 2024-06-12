package com.example.Proposta.web.exception;

import com.example.Proposta.excecao.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;




@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiExceptionHandler.class)
public class ApiExceptionHandlerTest {
    @Autowired
    private ApiExceptionHandler apiExceptionHandler;

    @Test
    public void handlePropostaNaoEncontradaException() {
        Long idProposta = 1L;
        PropostaNaoEncontradoException exception = new PropostaNaoEncontradoException(idProposta);
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handlerPropostaNaoEncontradaException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Proposta não encontrada com o ID: 1", response.getBody().getMessage());
    }

    @Test
    public void handlePropostaJaDesativadaException() {
        Long idProposta = 1L;
       PropostaJaDesativadaException exception = new PropostaJaDesativadaException(idProposta);
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handlePropostaJaDesativadaExceptionException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Proposta com o ID: 1 já está desabilitada", response.getBody().getMessage());
    }

    @Test
    public void handleFuncionarioNaoEncontradoException() {
        Long idProposta = 1L;
        FuncionarioNaoEncontradoException exception = new FuncionarioNaoEncontradoException(idProposta);
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handleFuncionarioNaoEncontradoException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Funcionario não encontrado com o ID: 1", response.getBody().getMessage());
    }

    @Test
    public void handleFuncionarioJaVotouException() {
        Long idProposta = 1L;
        FuncionarioJaVotouException exception = new FuncionarioJaVotouException(idProposta);
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handleFuncionarioJaVotouException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Funcionario com o ID: 1 já votou", response.getBody().getMessage());
    }

    @Test
    public void handlePropostaJaExisteException() {
        PropostaJaExisteException exception = new PropostaJaExisteException("Proposta já existe");
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handlePropostaJaExisteExceptionException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Proposta já existe", response.getBody().getMessage());
    }

    @Test
    public void handleRuntimeException() {
        RuntimeException exception = new RuntimeException("Erro interno do servidor");
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handleRuntimeException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno do servidor", response.getBody().getMessage());
    }
}
