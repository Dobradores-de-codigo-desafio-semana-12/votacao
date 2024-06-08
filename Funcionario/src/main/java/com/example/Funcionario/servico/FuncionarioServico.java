package com.example.Funcionario.servico;


import com.example.Funcionario.entidade.Funcionario;
import com.example.Funcionario.repositorio.FuncionarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FuncionarioServico {

    @Autowired
    private final FuncionarioRepositorio funcionarioRepositorio;

    @Transactional
    public Funcionario salvar(Funcionario fun) {
        return funcionarioRepositorio.save(fun);
    }

}
