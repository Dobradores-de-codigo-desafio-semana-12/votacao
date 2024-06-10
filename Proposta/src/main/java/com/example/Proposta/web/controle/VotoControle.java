package com.example.Proposta.web.controle;



import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.entidade.Voto;
import com.example.Proposta.servico.VotoServico;

import com.example.Proposta.web.dto.VotoCriarDto;
import com.example.Proposta.web.dto.VotoRespostaDto;
import com.example.Proposta.web.dto.mapa.PropostaMapa;
import com.example.Proposta.web.dto.mapa.VotoMapa;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/votos")
public class VotoControle {

    private final VotoServico votoServico;

    @PatchMapping("/proposta/{id}")
    public ResponseEntity<Void> iniciarVotos(@PathVariable Long id){
        votoServico.iniciarVotos(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping()
    public ResponseEntity<VotoRespostaDto> votar(@RequestBody VotoCriarDto dto){
       VotoRespostaDto respostaDto = votoServico.votar(dto);
        return ResponseEntity.ok(respostaDto);
    }

}
