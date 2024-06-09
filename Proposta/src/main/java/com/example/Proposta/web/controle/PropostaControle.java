package com.example.Proposta.web.controle;

import com.example.Proposta.servico.PropostaServico;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/proposta")
public class PropostaControle {

    private final PropostaServico propostaServico;
}
