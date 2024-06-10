package com.example.Proposta.servico;


import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.excecao.PropostaJaDesativadaException;
import com.example.Proposta.excecao.PropostaJaExisteException;
import com.example.Proposta.excecao.PropostaNaoEncontradoException;
import com.example.Proposta.repositorio.PropostaRepositorio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            return propostaRepositorio.save(proposta);
        } catch (DataIntegrityViolationException e) {
            throw new PropostaJaExisteException("Proposta com título '" + proposta.getTitulo() + "' já existe");
        }
    }

    @Transactional(readOnly = true)
    public Proposta buscarPorId(Long id) {
        return propostaRepositorio.findById(id).orElseThrow(
                () -> new PropostaNaoEncontradoException(id)
        );
    }


    @Transactional
    public void desabilitarProposta(Long id) {
        Proposta proposta = buscarPorId(id);
        if (!proposta.getAtivo()) {
            throw new PropostaJaDesativadaException(id);
        }
        proposta.setAtivo(false);
    }

    @Transactional(readOnly = true)
    public List<Proposta> buscarTodos() {
        return propostaRepositorio.findAll();
    }
    @Transactional(readOnly = true)
    public String buscarTituloPorId(Long id) {
        Proposta proposta = propostaRepositorio.findById(id)
                .orElseThrow(() -> new PropostaNaoEncontradoException(id));
        return proposta.getTitulo();
    }
}
