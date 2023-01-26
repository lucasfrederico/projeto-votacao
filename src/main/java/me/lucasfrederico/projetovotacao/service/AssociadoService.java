package me.lucasfrederico.projetovotacao.service;

import lombok.AllArgsConstructor;
import me.lucasfrederico.projetovotacao.domain.Associado;
import me.lucasfrederico.projetovotacao.dto.AssociadoDTO;
import me.lucasfrederico.projetovotacao.dto.NovoAssociadoDTO;
import me.lucasfrederico.projetovotacao.dto.StatusCPFParaVotacaoDTO;
import me.lucasfrederico.projetovotacao.integration.CpfValidadorComponent;
import me.lucasfrederico.projetovotacao.repository.AssociadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final CpfValidadorComponent cpfValidadorComponent;

    public AssociadoDTO salvarNovoAssociado(NovoAssociadoDTO novoAssociadoDTO) {
        if (associadoRepository.existsByNomeIgnoreCase(novoAssociadoDTO.getNome())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Associado já existe.");
        }

        var entity = new Associado();
        entity.setNome(novoAssociadoDTO.getNome());
        associadoRepository.save(entity);

        return AssociadoDTO.toDTO(associadoRepository.save(entity));
    }

    public Page<AssociadoDTO> listarAssociados(String search, Pageable pageable) {
        return associadoRepository.findByNomeContainsIgnoreCaseOrderByNomeAsc(search, pageable);
    }

    public void apagarAssociado(Long associadoId) {
        if (!associadoRepository.existsById(associadoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Associado não encontrado.");
        }

        associadoRepository.deleteById(associadoId);
    }

    public StatusCPFParaVotacaoDTO verificarCPFValidoParaVotacao(String cpf) {
        return new StatusCPFParaVotacaoDTO(cpfValidadorComponent.isValido(cpf));
    }
}
