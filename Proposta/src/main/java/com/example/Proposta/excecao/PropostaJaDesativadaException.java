package com.example.Proposta.excecao;

public class PropostaJaDesativadaException extends RuntimeException {
    public PropostaJaDesativadaException(Long id) {
        super(String.format("Proposta com o ID: %d já está desabilitada", id));
    }
}
