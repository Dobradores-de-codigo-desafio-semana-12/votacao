package com.example.Funcionario.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioRespostaDto {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private Boolean ativo;
}
