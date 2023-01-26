package me.lucasfrederico.projetovotacao.dto;

import lombok.*;
import me.lucasfrederico.projetovotacao.domain.Associado;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDTO {

    private Long id;
    private String nome;

    public static AssociadoDTO toDTO(Associado associado) {
        return builder().id(associado.getId()).nome(associado.getNome()).build();
    }

}
