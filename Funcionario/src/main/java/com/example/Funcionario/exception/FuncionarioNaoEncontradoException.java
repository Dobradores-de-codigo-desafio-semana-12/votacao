package com.example.Funcionario.exception;

import lombok.Getter;

@Getter
public class FuncionarioNaoEncontradoException extends RuntimeException{
    public FuncionarioNaoEncontradoException(Long id){
        super(String.format("Funcionario não encontrado com id %d", id));
    }
}
