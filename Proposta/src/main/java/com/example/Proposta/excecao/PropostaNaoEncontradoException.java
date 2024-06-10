package com.example.Proposta.excecao;

public class PropostaNaoEncontradoException extends RuntimeException {
    public PropostaNaoEncontradoException(Long id) {
        super(String.format("Proposta não encontrada com o ID: %d", id));
    }
}
