package com.example.Funcionario.repositorio;

import com.example.Funcionario.entidade.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long> {
}
