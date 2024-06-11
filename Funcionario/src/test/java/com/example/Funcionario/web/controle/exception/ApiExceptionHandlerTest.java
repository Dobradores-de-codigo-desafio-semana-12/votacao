package com.example.Funcionario.web.controle.exception;

import com.example.Funcionario.exception.*;
import com.example.Funcionario.web.exception.ApiExceptionHandler;
import com.example.Funcionario.web.exception.ErrorMessage;
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
    public void handleFuncionarioNaoSalvoException() {
        FuncionarioNaoSalvoException exception = new FuncionarioNaoSalvoException("Erro ao salvar funcionário");
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handleFuncionarioNaoSalvoException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao salvar funcionário", response.getBody().getMessage());
    }

    @Test
    public void handleFuncionarioNaoEncontradoException() {
        Long idFuncionario = 1L;
        FuncionarioNaoEncontradoException exception = new FuncionarioNaoEncontradoException(idFuncionario);
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handleFuncionarioNaoEncontradoException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Funcionario não encontrado com id " + idFuncionario, response.getBody().getMessage());
    }

    @Test
    public void handleFuncionarioAtualizarException() {
        Long idFuncionario = 1L;
        String mensagemErro = "Erro ao atualizar funcionário";
        FuncionarioAtualizarException exception = new FuncionarioAtualizarException(idFuncionario, mensagemErro);
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handleFuncionarioAtualizarException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(String.format("Erro ao atualizar funcionário com id %d: %s", idFuncionario, mensagemErro), response.getBody().getMessage());
    }

    @Test
    public void handleFuncionarioDesabilitarException() {
        Long idFuncionario = 1L;
        String mensagemErro = "Erro ao desabilitar funcionário";
        FuncionarioDesabilitarException exception = new FuncionarioDesabilitarException(idFuncionario, mensagemErro);
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handleFuncionarioDesabilitarException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(String.format("Erro ao desabilitar funcionário com id %d: %s", idFuncionario, mensagemErro), response.getBody().getMessage());
    }

    @Test
    public void handleFuncionarioListaException() {
        FuncionarioListaException exception = new FuncionarioListaException("Erro ao listar funcionários");
        ResponseEntity<ErrorMessage> response = apiExceptionHandler.handleFuncionarioListaException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao buscar lista de funcionários: Erro ao listar funcionários", response.getBody().getMessage());
    }
}
