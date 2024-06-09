package com.example.Proposta.repositorio;

import com.example.Proposta.entidade.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepositorio extends JpaRepository<Proposta, Long> {
}
