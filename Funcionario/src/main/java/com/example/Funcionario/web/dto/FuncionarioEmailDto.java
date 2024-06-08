package com.example.Funcionario.web.dto;

import jakarta.validation.constraints.Email;
import lombok.*;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class FuncionarioEmailDto {
    @Email
    private String novoEmail;
}
