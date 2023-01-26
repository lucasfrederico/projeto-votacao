package me.lucasfrederico.projetovotacao.controller;

import lombok.AllArgsConstructor;
import me.lucasfrederico.projetovotacao.dto.NovaPautaDTO;
import me.lucasfrederico.projetovotacao.dto.PautaDTO;
import me.lucasfrederico.projetovotacao.service.PautaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pauta")
public class PautaController {

    private final PautaService pautaService;

    public ResponseEntity<PautaDTO> salvarNovaPauta(NovaPautaDTO novaPautaDTO) {
        return ResponseEntity.ok(pautaService.salvarNovaPauta(novaPautaDTO));
    }
}
