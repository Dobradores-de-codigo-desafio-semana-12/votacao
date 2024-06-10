package com.example.Proposta.servico;


import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.repositorio.PropostaRepositorio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PropostaServico {

    @Autowired
    private final PropostaRepositorio propostaRepositorio;

    @Transactional
    public Proposta salvar(Proposta proposta) {

        return propostaRepositorio.save(proposta);
    }

    @Transactional(readOnly = true)
    public Proposta buscarPorId(Long id) {
        return propostaRepositorio.findById(id).orElseThrow(
                ()-> new RuntimeException(String.format("Proposta não encontrada", id))
        );
    }


    @Transactional
    public void desabilitarProposta(Long id) {
        Proposta fun = buscarPorId(id);
        fun.setAtivo(false);
    }

    @Transactional(readOnly = true)
    public List<Proposta> buscarTodos() {

        return propostaRepositorio.findAll();
    }
    @Transactional(readOnly = true)
    public String buscarTituloPorId(Long id) {
        Proposta proposta = propostaRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proposta não encontrada com o ID: " + id));
        return proposta.getTitulo();
    }
}
