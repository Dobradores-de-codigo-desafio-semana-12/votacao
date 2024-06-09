package com.example.Proposta.servico;


import com.example.Proposta.entidade.Voto;
import com.example.Proposta.repositorio.VotoRepositorio;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Getter
@RequiredArgsConstructor
@Service
public class VotoServico {

    private final PropostaServico propostaServico;
    private final VotoRepositorio votoRepositorio;



}
