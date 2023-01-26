package me.lucasfrederico.projetovotacao.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.lucasfrederico.projetovotacao.dto.AssociadoDTO;
import me.lucasfrederico.projetovotacao.dto.NovoAssociadoDTO;
import me.lucasfrederico.projetovotacao.dto.StatusCPFParaVotacaoDTO;
import me.lucasfrederico.projetovotacao.service.AssociadoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/associados")
public class AssociadoController {

    private final AssociadoService associadoService;

    @PostMapping
    public ResponseEntity<AssociadoDTO> salvarNovoAssociado(@RequestBody @Valid NovoAssociadoDTO novoAssociadoDTO) {
        return ResponseEntity.ok(associadoService.salvarNovoAssociado(novoAssociadoDTO));
    }

    @GetMapping
    public ResponseEntity<Page<AssociadoDTO>> listarAssociados(@Param("search") String search, Pageable pageable) {
        return ResponseEntity.ok(associadoService.listarAssociados(search, pageable));
    }

    @GetMapping("/verificar-cpf-valido/{cpf}")
    public ResponseEntity<StatusCPFParaVotacaoDTO> verificarCPFValidoParaVotacao(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(associadoService.verificarCPFValidoParaVotacao(cpf));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarAssociado(@PathVariable("id") Long id) {
        associadoService.apagarAssociado(id);
        return ResponseEntity.noContent().build();
    }
}
