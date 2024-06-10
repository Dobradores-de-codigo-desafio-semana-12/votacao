package com.example.Proposta.web.dto;

import com.example.Proposta.entidade.Proposta;
import com.example.Proposta.entidade.Voto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotoRespostaDto {

    private String propostaTitulo;
    private int aprovar;
    private int rejeitar;

}
