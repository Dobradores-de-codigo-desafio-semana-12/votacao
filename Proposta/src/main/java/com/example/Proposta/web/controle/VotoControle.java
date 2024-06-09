package com.example.Proposta.web.controle;


import com.example.Proposta.servico.VotoServico;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/votos")
public class VotoControle {

    private final VotoServico votoServico;


}
