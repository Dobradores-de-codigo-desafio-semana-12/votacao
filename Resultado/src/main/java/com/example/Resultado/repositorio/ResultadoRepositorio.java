package com.example.Resultado.repositorio;

import com.example.Resultado.dto.VotoRespostaDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultadoRepositorio extends JpaRepository<VotoRespostaDto, Long> {
}
