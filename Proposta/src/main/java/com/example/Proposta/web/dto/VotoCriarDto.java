package com.example.Proposta.web.dto;

import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.entidade.Voto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class VotoCriarDto {

    private Long propostaId;

    private String funcionarioId;

    @Enumerated(EnumType.STRING)
    private Voto votar;
}
