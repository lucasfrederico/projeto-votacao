package me.lucasfrederico.projetovotacao.dto;

import lombok.Data;
import me.lucasfrederico.projetovotacao.domain.enums.ResultadoVotacaoEnum;

@Data
public class ResultadoVotacaoDTO {

    private ResultadoVotacaoEnum resultadoVotacaoEnum;
    private int totalSim;
    private int totalNao;

    public void processarResultado(int totalSim, int totalNao) {
        if (totalSim == totalNao) {
            resultadoVotacaoEnum = ResultadoVotacaoEnum.EMPATE;
        } else if (totalSim > totalNao) {
            resultadoVotacaoEnum = ResultadoVotacaoEnum.SIM;
        } else {
            resultadoVotacaoEnum = ResultadoVotacaoEnum.NAO;
        }

        this.totalSim = totalSim;
        this.totalNao = totalNao;
    }

    public int getTotalVotos() {
        return totalSim + totalNao;
    }

    public int getTotalVotosVencedor() {
        switch (resultadoVotacaoEnum) {
            case SIM -> {
                return totalSim;
            }
            case NAO -> {
                return totalNao;
            }
            case EMPATE -> {
                return getTotalVotos();
            }
            default -> throw new IllegalStateException("Unexpected value: " + resultadoVotacaoEnum);
        }
    }

}
