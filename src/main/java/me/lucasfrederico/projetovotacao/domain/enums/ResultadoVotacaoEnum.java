package me.lucasfrederico.projetovotacao.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultadoVotacaoEnum {

    SIM("Sim"),
    NAO("Não"),
    EMPATE("Empate");

    private final String description;

}
