package com.example.Funcionario.exception;

public class FuncionarioListaException extends RuntimeException{
    public FuncionarioListaException(String message){
        super(String.format("Erro ao buscar lista de funcionários: %s", message));
    }
}
