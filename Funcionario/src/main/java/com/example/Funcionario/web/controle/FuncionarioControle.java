package com.example.Funcionario.web.controle;

import com.example.Funcionario.entidade.Funcionario;
import com.example.Funcionario.servico.FuncionarioServico;
import com.example.Funcionario.web.dto.FuncionarioCriarDto;
import com.example.Funcionario.web.dto.FuncionarioRespostaDto;
import com.example.Funcionario.web.dto.mapa.FuncionarioMapa;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/funcionarios")
public class FuncionarioControle {

    private final FuncionarioServico funcionarioServico;

    @PostMapping
    public ResponseEntity<FuncionarioRespostaDto> cadastrarFuncionario(@Valid @RequestBody FuncionarioCriarDto dto){
        Funcionario fun = funcionarioServico.salvar(FuncionarioMapa.paraFuncionario(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(FuncionarioMapa.paraDto(fun));
    }

}
