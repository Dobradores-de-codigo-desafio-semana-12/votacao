package com.example.Proposta.excecao;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException(Long id) {
        super(String.format("Funcionario não encontrado com o ID: %d", id));
    }
}
