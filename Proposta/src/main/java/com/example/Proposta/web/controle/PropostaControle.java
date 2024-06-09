package com.example.Proposta.web.controle;

import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.servico.PropostaServico;
import com.example.Proposta.web.dto.PropostaCriarDto;
import com.example.Proposta.web.dto.PropostaRespostaDto;
import com.example.Proposta.web.dto.mapa.PropostaMapa;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/propostas")
public class PropostaControle {

    private final PropostaServico propostaServico;

    @PostMapping
    public ResponseEntity<PropostaRespostaDto> cadastrarProposta(@Valid @RequestBody PropostaCriarDto dto){
        Proposta proposta = propostaServico.salvar(PropostaMapa.paraProposta(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(PropostaMapa.paraDto(proposta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaRespostaDto> buscarProposta(@PathVariable Long id){
        Proposta proposta = propostaServico.buscarPorId(id);
        return ResponseEntity.ok(PropostaMapa.paraDto(proposta));
    }


    @PatchMapping("/desabilitar/{id}")
    public ResponseEntity<Void> desabilitarProposta(@PathVariable Long id) {
        propostaServico.desabilitarProposta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PropostaRespostaDto>> getAll() {
        List<Proposta> propostas = propostaServico.buscarTodos();
        return ResponseEntity.ok(PropostaMapa.paraListaDto(propostas));
    }

}
