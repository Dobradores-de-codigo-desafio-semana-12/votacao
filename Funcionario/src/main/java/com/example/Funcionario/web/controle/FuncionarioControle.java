package com.example.Funcionario.web.controle;

import com.example.Funcionario.servico.FuncionarioServico;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/funcionarios")
public class FuncionarioControle {

    private final FuncionarioServico funcionarioServico;
}
