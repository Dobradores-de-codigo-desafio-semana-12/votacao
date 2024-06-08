package com.example.Funcionario.web.controle;

import com.example.Funcionario.entidade.Funcionario;
import com.example.Funcionario.servico.FuncionarioServico;
import com.example.Funcionario.web.dto.FuncionarioCriarDto;
import com.example.Funcionario.web.dto.FuncionarioEmailDto;
import com.example.Funcionario.web.dto.FuncionarioRespostaDto;
import com.example.Funcionario.web.dto.mapa.FuncionarioMapa;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioRespostaDto> buscarFuncionario(@PathVariable Long id){
        Funcionario fun = funcionarioServico.buscarPorId(id);
        return ResponseEntity.ok(FuncionarioMapa.paraDto(fun));
    }
    @PatchMapping("/email/{id}")
    public ResponseEntity<FuncionarioRespostaDto> atualizarEmail(@PathVariable Long id, @RequestBody FuncionarioEmailDto dto) {
        Funcionario fun = funcionarioServico.editarEmail(id, dto.getNovoEmail());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(FuncionarioMapa.paraDto(fun));
    }

}
