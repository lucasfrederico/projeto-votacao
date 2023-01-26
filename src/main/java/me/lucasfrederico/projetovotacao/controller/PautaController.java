package me.lucasfrederico.projetovotacao.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.lucasfrederico.projetovotacao.dto.NovaPautaDTO;
import me.lucasfrederico.projetovotacao.dto.PautaDTO;
import me.lucasfrederico.projetovotacao.dto.PautaResumoDTO;
import me.lucasfrederico.projetovotacao.dto.VotoDTO;
import me.lucasfrederico.projetovotacao.service.PautaService;
import me.lucasfrederico.projetovotacao.service.VotoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/pautas")
public class PautaController {

    private final PautaService pautaService;
    private final VotoService votoService;

    @PostMapping
    public ResponseEntity<PautaDTO> salvarNovaPauta(@RequestBody @Valid NovaPautaDTO novaPautaDTO) {
        return ResponseEntity.ok(pautaService.salvarNovaPauta(novaPautaDTO));
    }

    @GetMapping("/{pautaId}/votos")
    public ResponseEntity<Page<VotoDTO>> listarVotos(@PathVariable("pautaId") Long pautaId, Pageable pageable) {
        return ResponseEntity.ok(votoService.listarVotos(pautaId, pageable));
    }

    @GetMapping("/{pautaId}")
    public ResponseEntity<PautaResumoDTO> mostrarResumoPauta(@PathVariable("pautaId") Long pautaId) {
        return ResponseEntity.ok(pautaService.mostrarResumoPauta(pautaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarPauta(@PathVariable("id") Long id) {
        pautaService.apagarPauta(id);
        return ResponseEntity.noContent().build();
    }
}
