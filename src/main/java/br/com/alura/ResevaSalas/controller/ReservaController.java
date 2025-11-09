package br.com.alura.ResevaSalas.controller;

import br.com.alura.ResevaSalas.dto.ReservaCancelDTO;
import br.com.alura.ResevaSalas.dto.ReservaRequestDTO;
import br.com.alura.ResevaSalas.model.Reserva;
import br.com.alura.ResevaSalas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {
    @Autowired
    private ReservaService service;

    @PostMapping
    public ResponseEntity criarReserva(@RequestBody ReservaRequestDTO dto, UriComponentsBuilder uriBuilder) {
        Reserva novaReserva = service.criarReserva(dto);

        var uri = uriBuilder.path("reservas/{id}").buildAndExpand(novaReserva.getId());
        return ResponseEntity.created(uri.toUri()).body(novaReserva);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelarReserva(@PathVariable Long id, @RequestBody ReservaCancelDTO dto) {
        Reserva reservaCancelada = service.cancelarReserva(id, dto.motivoCancelamento());
        return ResponseEntity.ok(reservaCancelada);
    }
}
