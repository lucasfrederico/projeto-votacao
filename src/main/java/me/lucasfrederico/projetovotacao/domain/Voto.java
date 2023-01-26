package me.lucasfrederico.projetovotacao.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.lucasfrederico.projetovotacao.domain.enums.VotoOpcaoEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@RequiredArgsConstructor
public class Voto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_pauta", referencedColumnName = "id", nullable = false)
    private Pauta pauta;

    @ManyToOne
    @JoinColumn(name = "fk_associado", referencedColumnName = "id", nullable = false)
    private Associado associado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(200)")
    private VotoOpcaoEnum opcao;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    private LocalDateTime dataVoto;
}
