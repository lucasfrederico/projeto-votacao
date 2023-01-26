package me.lucasfrederico.projetovotacao.service;

import lombok.RequiredArgsConstructor;
import me.lucasfrederico.projetovotacao.domain.Pauta;
import me.lucasfrederico.projetovotacao.domain.enums.VotoOpcaoEnum;
import me.lucasfrederico.projetovotacao.dto.ResultadoVotacaoDTO;
import me.lucasfrederico.projetovotacao.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * {@link Component } responsável por processar o resultado final de uma votação.
 *
 * @see ResultadoVotacaoDTO
 * @see PautaService
 * @see VotoService
 * @see VotoRepository
 */
@Component
@RequiredArgsConstructor
public class ResultadoVotacaoComponent {

    private final VotoRepository votoRepository;

    /**
     * Obter as informações do resultado final de uma votação.
     *
     * @param pauta Pauta para analisar o resultado final.
     */
    public ResultadoVotacaoDTO obterResultadoVotacaoOpcao(Pauta pauta) {
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
