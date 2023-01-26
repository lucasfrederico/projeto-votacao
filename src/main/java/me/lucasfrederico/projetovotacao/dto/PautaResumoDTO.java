package me.lucasfrederico.projetovotacao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import me.lucasfrederico.projetovotacao.domain.enums.ResultadoVotacaoEnum;

import java.time.LocalDateTime;

@Data
public class PautaResumoDTO {

    private Long id;

    private String titulo;

    private String descricao;

    @JsonProperty(value = "total_sim")
    private Integer totalSim;

    @JsonProperty(value = "total_nao")
    private Integer totalNao;

    @JsonProperty(value = "total_votos")
    private Integer totalVotos;

    @JsonProperty(value = "resultado_final")
    private ResultadoVotacaoEnum resultadoFinal;

    @JsonProperty(value = "data_inicio")
    private LocalDateTime dataInicio;

    @JsonProperty(value = "data_termino")
    private LocalDateTime dataTermino;

}
