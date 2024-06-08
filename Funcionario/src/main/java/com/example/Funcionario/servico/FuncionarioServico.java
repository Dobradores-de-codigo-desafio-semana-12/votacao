package com.example.Funcionario.servico;


import com.example.Funcionario.repositorio.FuncionarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FuncionarioServico {

    @Autowired
    private final FuncionarioRepositorio funcionarioRepositorio;
}
