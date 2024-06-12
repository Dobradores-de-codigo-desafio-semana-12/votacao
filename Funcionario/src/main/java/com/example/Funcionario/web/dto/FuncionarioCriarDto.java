package com.example.Funcionario.web.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor @Getter @Setter @ToString
public class FuncionarioCriarDto {

    @NotBlank
    private String nome;

    @Size(min = 11, max = 11)
    @CPF
    private String cpf;
    @NotBlank
    @Email
    private String email;

}
