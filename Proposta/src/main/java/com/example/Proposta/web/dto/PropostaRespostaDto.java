package com.example.Proposta.web.dto;

import lombok.*;


@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PropostaRespostaDto {

    private Long id;
    private String titulo;
    private String descricao;
    private Boolean ativo;
}
