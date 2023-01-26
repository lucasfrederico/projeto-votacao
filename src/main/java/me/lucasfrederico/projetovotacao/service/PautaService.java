package me.lucasfrederico.projetovotacao.service;

import lombok.AllArgsConstructor;
import me.lucasfrederico.projetovotacao.domain.Pauta;
import me.lucasfrederico.projetovotacao.domain.enums.VotoOpcaoEnum;
import me.lucasfrederico.projetovotacao.dto.NovaPautaDTO;
import me.lucasfrederico.projetovotacao.dto.PautaDTO;
import me.lucasfrederico.projetovotacao.dto.PautaResumoDTO;
import me.lucasfrederico.projetovotacao.repository.PautaRepository;
import me.lucasfrederico.projetovotacao.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

/**
 * {@link Service } responsável por concentrar a regra de negócio do domínio {@link Pauta}.
 *
 * @see PautaDTO
 * @see ResultadoVotacaoComponent
 */
@Service
@AllArgsConstructor
public class PautaService {

    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;
    private final ResultadoVotacaoComponent resultadoVotacaoComponent;

    /**
     * Salvar uma nova pauta.
     *
     * @param novaPautaDTO Nova pauta à ser salva.
     */
    public PautaDTO salvarNovaPauta(NovaPautaDTO novaPautaDTO) {
        if (pautaRepository.existsByTituloIgnoreCase(novaPautaDTO.getTitulo())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma votação com esse título.");
        }

        var entity = new Pauta();
        entity.setTitulo(novaPautaDTO.getTitulo());
        entity.setDescricao(novaPautaDTO.getDescricao());
        entity.setDataInicio(LocalDateTime.now());
        entity.setDataTermino(LocalDateTime.now().plusSeconds(novaPautaDTO.getSegundosDuracao()));

        return PautaDTO.toDTO(pautaRepository.save(entity));
    }

    /**
     * Mostrar resumo de uma pauta por ID.
     *
     * @param pautaId ID da pauta.
     */
    public PautaResumoDTO mostrarResumoPauta(Long pautaId) {
        if (!pautaRepository.existsById(pautaId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Votação não encontrada.");
        }

        var pauta = pautaRepository.findById(pautaId).orElseThrow();
        var pautaResumo = new PautaResumoDTO();
        pautaResumo.setId(pautaId);
        pautaResumo.setTitulo(pauta.getTitulo());
        pautaResumo.setDescricao(pauta.getDescricao());
        pautaResumo.setDataInicio(pauta.getDataInicio());
        pautaResumo.setDataTermino(pauta.getDataTermino());

        if (pauta.prazoJaEncerrou()) {
            var resultadoFinal = resultadoVotacaoComponent.obterResultadoVotacaoOpcao(pauta);
            pautaResumo.setResultadoFinal(resultadoFinal.getResultadoVotacaoEnum());
            pautaResumo.setTotalSim(resultadoFinal.getTotalSim());
            pautaResumo.setTotalNao(resultadoFinal.getTotalNao());
            pautaResumo.setTotalVotos(resultadoFinal.getTotalVotos());
        } else {
            var totalSim = votoRepository.countByPauta_IdAndOpcao(pautaId, VotoOpcaoEnum.SIM);
            var totalNao = votoRepository.countByPauta_IdAndOpcao(pautaId, VotoOpcaoEnum.NAO);
            pautaResumo.setTotalSim(totalSim);
            pautaResumo.setTotalNao(totalNao);
            pautaResumo.setTotalVotos(totalSim + totalNao);
        }

        return pautaResumo;
    }

    /**
     * Apagar pauta por ID.
     *
     * @param pautaId ID da pauta.
     */
    public void apagarPauta(Long pautaId) {
        if (!pautaRepository.existsById(pautaId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pauta não encontrada.");
        }

        pautaRepository.deleteById(pautaId);
    }


}
