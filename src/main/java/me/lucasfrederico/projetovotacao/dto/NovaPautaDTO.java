package me.lucasfrederico.projetovotacao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class NovaPautaDTO {

    @Length(min = 5, max = 40, message = "campo.titulo.length")
    @NotEmpty(message = "campo.titulo.notempty")
    @NotNull(message = "campo.titulo.required")
    private String titulo;

    @Length(min = 10, max = 200, message = "campo.descricao.length")
    @NotEmpty(message = "campo.descricao.notempty")
    private String descricao;

    @JsonProperty(value = "segundos_duracao")
    @Min(value = 1)
    @NotNull(message = "campo.segundosDuracao.required")
    private Integer segundosDuracao = 60;

}
