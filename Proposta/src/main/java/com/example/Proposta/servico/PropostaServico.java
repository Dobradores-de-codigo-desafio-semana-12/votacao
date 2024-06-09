package com.example.Proposta.servico;


import com.example.Proposta.repositorio.PropostaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PropostaServico {

    @Autowired
    private final PropostaRepositorio propostaRepositorio;
}
