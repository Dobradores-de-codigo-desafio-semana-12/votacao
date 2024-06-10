package com.example.Proposta.excecao;

public class FuncionarioJaVotouException extends RuntimeException {
    public FuncionarioJaVotouException(Long id) {
        super(String.format("Funcionario com o ID: %d já votou", id));
    }
}
