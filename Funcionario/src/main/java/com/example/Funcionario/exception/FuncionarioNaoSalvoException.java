package com.example.Funcionario.exception;

public class FuncionarioNaoSalvoException extends FuncionarioException{
    public FuncionarioNaoSalvoException(String message) {
        super(String.format("Erro ao salvar funcinário: %s", message));
    }
}
