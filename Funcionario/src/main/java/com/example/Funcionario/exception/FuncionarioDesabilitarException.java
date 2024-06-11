package com.example.Funcionario.exception;

public class FuncionarioDesabilitarException extends RuntimeException{
    public FuncionarioDesabilitarException(Long id, String message){
        super(String.format("Erro ao desabilitar funcionário com id %d: %s",id, message));
    }
}