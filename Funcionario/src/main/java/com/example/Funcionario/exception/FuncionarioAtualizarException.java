package com.example.Funcionario.exception;

public class FuncionarioAtualizarException extends RuntimeException{
    public FuncionarioAtualizarException(Long id, String message){
        super(String.format("Erro ao atualizar funcionário com id %d: %s", id, message));
    }
}
