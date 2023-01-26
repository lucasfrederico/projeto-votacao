package me.lucasfrederico.projetovotacao.dto;

import lombok.Data;

@Data
public class StatusCPFParaVotacaoDTO {

    private StatusCPFEnum status;

    public StatusCPFParaVotacaoDTO(boolean isCpfValido) {
        this.status = isCpfValido ? StatusCPFEnum.ABLE_TO_VOTE : StatusCPFEnum.UNABLE_TO_VOTE;
    }

    public enum StatusCPFEnum {
        ABLE_TO_VOTE,
        UNABLE_TO_VOTE;
    }

}
