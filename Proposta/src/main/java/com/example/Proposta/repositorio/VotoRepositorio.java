package com.example.Proposta.repositorio;

import com.example.Proposta.entidade.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepositorio extends JpaRepository<Voto, Long> {

    int countByVotar(Voto.Votar votar);


    List<Voto> findByFuncionarioId(Long funcionarioId);
}
