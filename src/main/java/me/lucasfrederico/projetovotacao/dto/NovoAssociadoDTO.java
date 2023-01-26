package me.lucasfrederico.projetovotacao.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NovoAssociadoDTO {

    @NotNull(message = "campo.nome.required")
    @NotEmpty(message = "campo.nome.notempty")
    private String nome;

}
