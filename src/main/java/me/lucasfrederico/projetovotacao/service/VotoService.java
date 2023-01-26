package me.lucasfrederico.projetovotacao.service;

import lombok.AllArgsConstructor;
import me.lucasfrederico.projetovotacao.domain.Voto;
import me.lucasfrederico.projetovotacao.domain.enums.VotoOpcaoEnum;
import me.lucasfrederico.projetovotacao.dto.NovoVotoDTO;
import me.lucasfrederico.projetovotacao.dto.VotoDTO;
import me.lucasfrederico.projetovotacao.repository.AssociadoRepository;
import me.lucasfrederico.projetovotacao.repository.PautaRepository;
import me.lucasfrederico.projetovotacao.repository.VotoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VotoService {

    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;
    private final AssociadoRepository associadoRepository;
    private final ResultadoVotacaoComponent resultadoVotacaoComponent;

    public VotoDTO cadastrarNovoVoto(NovoVotoDTO novoVotoDTO) {
        if (!associadoRepository.existsById(novoVotoDTO.getAssociadoId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Associado não encontrado.");
        }

        if (!pautaRepository.existsById(novoVotoDTO.getVotacaoId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Votação não encontrada.");
        }

        if (votoRepository.existsByAssociado_IdAndPauta_Id(novoVotoDTO.getAssociadoId(), novoVotoDTO.getVotacaoId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O associado já realizou o seu voto nesta votação.");
        }

        var pauta = pautaRepository.findById(novoVotoDTO.getVotacaoId()).orElseThrow();
        if (pauta.prazoJaEncerrou()) {
            var resultadoPauta = resultadoVotacaoComponent.getResultadoVotacaoOpcao(pauta);

            var sessaoEncerradaMensagem = String.format(
                    "A sessão dessa pauta já foi finalizada e o resultado foi: %s com %s votos (%s votos no total).",
                    resultadoPauta.getResultadoVotacaoEnum().getDescription(),
                    resultadoPauta.getTotalVotosVencedor(),
                    resultadoPauta.getTotalVotos()
            );

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, sessaoEncerradaMensagem);
        }

        var entity = new Voto();
        entity.setAssociado(associadoRepository.findById(novoVotoDTO.getAssociadoId()).orElseThrow());
        entity.setPauta(pautaRepository.findById(novoVotoDTO.getVotacaoId()).orElseThrow());
        entity.setOpcao(VotoOpcaoEnum.ofTexto(novoVotoDTO.getTextoVoto()));
        entity.setDataVoto(LocalDateTime.now());

        return VotoDTO.toDTO(votoRepository.save(entity));
    }

    public Page<VotoDTO> listarVotos(Long pautaId, Pageable pageable) {
        if (!pautaRepository.existsById(pautaId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Votação não encontrada.");
        }

        return votoRepository.findAllByPautaId(pautaId, pageable);
    }

    public void apagarVoto(Long votoId) {
        if (!votoRepository.existsById(votoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Voto não encontrado.");
        }

        votoRepository.deleteById(votoId);
    }

}
