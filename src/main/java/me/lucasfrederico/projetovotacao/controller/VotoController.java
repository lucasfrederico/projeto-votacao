package me.lucasfrederico.projetovotacao.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.lucasfrederico.projetovotacao.dto.NovoVotoDTO;
import me.lucasfrederico.projetovotacao.dto.VotoDTO;
import me.lucasfrederico.projetovotacao.service.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/votos")
public class VotoController {

    private final VotoService votoService;

    @PostMapping
    public ResponseEntity<VotoDTO> cadastrarNovoVoto(@RequestBody @Valid NovoVotoDTO novoVotoDTO) {
        return ResponseEntity.ok(votoService.cadastrarNovoVoto(novoVotoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarVoto(@PathVariable("id") Long id) {
        votoService.apagarVoto(id);
        return ResponseEntity.noContent().build();
    }
}
