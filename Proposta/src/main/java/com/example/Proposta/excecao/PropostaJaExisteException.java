package com.example.Proposta.excecao;

public class PropostaJaExisteException extends RuntimeException {
    public PropostaJaExisteException(String message) {
        super(message);
    }
}
