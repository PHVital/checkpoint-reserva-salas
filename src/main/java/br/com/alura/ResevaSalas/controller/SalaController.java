package br.com.alura.ResevaSalas.controller;

import br.com.alura.ResevaSalas.dto.SalaRequestDTO;
import br.com.alura.ResevaSalas.dto.SalaResponseDTO;
import br.com.alura.ResevaSalas.model.Sala;
import br.com.alura.ResevaSalas.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/salas")
public class SalaController {
    @Autowired
    private SalaService service;

    @GetMapping("/disponiveis")
    public ResponseEntity<List<SalaResponseDTO>> salasDisponiveis() {
        List<Sala> salas = service.buscarSalasDisponiveis();
        List<SalaResponseDTO> dtos = salas.stream().map(SalaResponseDTO::new).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> salaPorId(@PathVariable Long id) {
        Sala sala = service.buscarSalaPorId(id);
        return ResponseEntity.ok(new SalaResponseDTO(sala));
    }

    @PostMapping
    public ResponseEntity<SalaResponseDTO> criarSala(@RequestBody SalaRequestDTO dto, UriComponentsBuilder uriBuilder) {
        Sala novaSala = service.criarSala(dto);
        var uri = uriBuilder.path("/salas/{id}").buildAndExpand(novaSala.getId()).toUri();
        return ResponseEntity.created(uri).body(new SalaResponseDTO(novaSala));
    }

    @PutMapping
    public ResponseEntity<SalaResponseDTO> atualizarSala(@PathVariable Long id, @RequestBody SalaRequestDTO dto) {
        Sala salaAtualizada = service.atualizarSala(id, dto);
        return ResponseEntity.ok(new SalaResponseDTO(salaAtualizada));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarSala(@PathVariable Long id) {
        service.deletarSala(id);
        return ResponseEntity.noContent().build();
    }
}
