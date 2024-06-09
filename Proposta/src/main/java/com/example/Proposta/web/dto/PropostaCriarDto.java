package com.example.Proposta.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class PropostaCriarDto {

    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;


}
