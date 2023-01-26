package me.lucasfrederico.projetovotacao.dto;

import lombok.Builder;
import lombok.Data;
import me.lucasfrederico.projetovotacao.domain.Pauta;

import java.time.LocalDateTime;

@Data
@Builder
public class PautaDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;

    public static PautaDTO toDTO(Pauta pauta) {
        return builder()
                .id(pauta.getId())
                .titulo(pauta.getTitulo())
                .descricao(pauta.getDescricao())
                .dataInicio(pauta.getDataInicio())
                .dataTermino(pauta.getDataTermino())
                .build();
    }

}
