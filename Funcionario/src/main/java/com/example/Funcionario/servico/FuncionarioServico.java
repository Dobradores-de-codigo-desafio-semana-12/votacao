package com.example.Funcionario.servico;


import com.example.Funcionario.entidade.Funcionario;
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
        return funcionarioRepositorio.save(fun);
    }
    @Transactional(readOnly = true)
    public Funcionario buscarPorId(Long id) {
        return funcionarioRepositorio.findById(id).orElseThrow(
                ()-> new RuntimeException(String.format("Funcionario não encontrado", id))
        );
    }

    @Transactional
    public Funcionario editarEmail(Long id, String novoEmail) {
        Funcionario fun = buscarPorId(id);
        fun.setEmail(novoEmail);
        return fun;
    }

    @Transactional
    public void habilitarFuncionario(Long id) {
        Funcionario fun = buscarPorId(id);
        fun.setAtivo(true);
    }

    @Transactional
    public void desabilitarFuncionario(Long id) {
        Funcionario fun = buscarPorId(id);
        fun.setAtivo(false);
    }
    @Transactional(readOnly = true)
    public List<Funcionario> buscarTodos() {
        return funcionarioRepositorio.findAll();
    }

}
