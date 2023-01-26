package me.lucasfrederico.projetovotacao.service;

import lombok.RequiredArgsConstructor;
import me.lucasfrederico.projetovotacao.domain.Pauta;
import me.lucasfrederico.projetovotacao.domain.enums.VotoOpcaoEnum;
import me.lucasfrederico.projetovotacao.dto.ResultadoVotacaoDTO;
import me.lucasfrederico.projetovotacao.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class ResultadoVotacaoComponent {

    private final VotoRepository votoRepository;

    public ResultadoVotacaoDTO getResultadoVotacaoOpcao(Pauta pauta) {
        if (!pauta.prazoJaEncerrou()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A votação ainda não terminou.");
        }

        var totalSim = votoRepository.countByPauta_IdAndOpcao(pauta.getId(), VotoOpcaoEnum.SIM);
        var totalNao = votoRepository.countByPauta_IdAndOpcao(pauta.getId(), VotoOpcaoEnum.NAO);

        var resultado = new ResultadoVotacaoDTO();
        resultado.processarResultado(totalSim, totalNao);
        return resultado;
    }
}
