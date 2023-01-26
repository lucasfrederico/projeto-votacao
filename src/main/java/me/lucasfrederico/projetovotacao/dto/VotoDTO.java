package me.lucasfrederico.projetovotacao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import me.lucasfrederico.projetovotacao.domain.Voto;
import me.lucasfrederico.projetovotacao.domain.enums.VotoOpcaoEnum;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class VotoDTO {
    private Long id;

    private PautaDTO pauta;

    private AssociadoDTO associado;

    @JsonProperty(value = "data_voto")
    private LocalDateTime dataVoto;

    private VotoOpcaoEnum opcao;

    public static VotoDTO toDTO(Voto voto) {
        return builder()
                .id(voto.getId())
                .associado(AssociadoDTO.toDTO(voto.getAssociado()))
                .pauta(PautaDTO.toDTO(voto.getPauta()))
                .dataVoto(voto.getDataVoto())
                .opcao(voto.getOpcao())
                .build();
    }

    public VotoDTO(Long id, LocalDateTime dataVoto, VotoOpcaoEnum opcao, Long associadoId, String nomeAssociado) {
        this.id = id;
        this.dataVoto = dataVoto;
        this.opcao = opcao;
        this.associado = new AssociadoDTO(associadoId, nomeAssociado);
    }
}