package com.example.Proposta.excecao;

public class PropostaInativadaException extends RuntimeException {
    public PropostaInativadaException(Long id) {
        super(String.format("Proposta com o ID: %d está inativa", id));
    }
}
