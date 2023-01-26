package me.lucasfrederico.projetovotacao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class NovoVotoDTO {

    @JsonProperty(value = "associado_id")
    @NotNull(message = "campo.associado_id.required")
    private Long associadoId;

    @JsonProperty(value = "votacao_id")
    @NotNull(message = "campo.votacao_id.required")
    private Long votacaoId;

    @JsonProperty(value = "voto")
    @NotEmpty(message = "campo.voto.invalido")
    @NotNull(message = "campo.voto.invalido")
    @Pattern(regexp = "^(Sim|Não)$", message = "campo.voto.invalido")
    private String textoVoto;

}
