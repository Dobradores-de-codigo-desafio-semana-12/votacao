package com.example.Proposta.entidade;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Setter @Getter @RequiredArgsConstructor @AllArgsConstructor @EqualsAndHashCode
@Table(name = "votos")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Voto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proposta_titulo")
    private Proposta propostaTitulo;

    @Column(name = "funcionario_Id")
    private Long funcionarioId;

    @Column(name = "votar")
    private Votar votar;

    public enum Votar {
        APROVAR, REJEITAR;
    }
}
