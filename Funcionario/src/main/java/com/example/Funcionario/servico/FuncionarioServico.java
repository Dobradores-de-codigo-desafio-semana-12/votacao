package com.example.Funcionario.servico;


import com.example.Funcionario.entidade.Funcionario;
import com.example.Funcionario.exception.*;
import com.example.Funcionario.repositorio.FuncionarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FuncionarioServico {

    @Autowired
    private final FuncionarioRepositorio funcionarioRepositorio;

    @Transactional
    public Funcionario salvar(Funcionario fun) {
        try {
            return funcionarioRepositorio.save(fun);
        }catch (Exception e) {
            throw  new FuncionarioNaoSalvoException(e.getMessage());
        }
    }
    @Transactional(readOnly = true)
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepositorio.findById(id).orElseThrow(
                ()-> new FuncionarioNaoEncontradoException(id)
        );
    }
    @Transactional
    public Funcionario editarEmail(Long id, String novoEmail) {
        Funcionario fun = buscarPorId(id);
        try {
            fun.setEmail(novoEmail);
            return fun;
        }catch (Exception e){
            throw new FuncionarioAtualizarException(id, e.getMessage());
        }
    }
    @Transactional
    public void desabilitarFuncionario(Long id) {
        Funcionario fun = buscarPorId(id);
        try {
            fun.setAtivo(false);
        }catch (Exception e){
            throw new FuncionarioDesabilitarException(id, e.getMessage());
        }
    }
    @Transactional(readOnly = true)
    public List<Funcionario> buscarTodos() {
        try {
            return funcionarioRepositorio.findAll();
        }catch (Exception e){
            throw new FuncionarioListaException(e.getMessage());
        }
    }

}
