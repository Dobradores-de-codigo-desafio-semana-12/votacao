package com.example.Proposta.entidade;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


@EqualsAndHashCode
@AllArgsConstructor
@Getter @Setter @RequiredArgsConstructor
@Table(name = "propostas")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Proposta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @Column(name = "descricao", nullable = false, length = 250)
    private String descricao;
    @Column(name = "tempo")
    private long tempo = 2;
    @Column(name = "ativo", nullable = false, length = 100)
    private Boolean ativo = true;

}
