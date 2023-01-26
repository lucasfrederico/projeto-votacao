package me.lucasfrederico.projetovotacao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.type.descriptor.DateTimeUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Pauta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime dataInicio;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime dataTermino;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pauta")
    private List<Voto> votos = new ArrayList<>();

    public boolean prazoJaEncerrou() {
        var now = LocalDateTime.now();
        return now.isAfter(dataTermino) || now.isEqual(dataTermino);
    }

}
