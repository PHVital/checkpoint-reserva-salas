package br.com.alura.ResevaSalas.dto;

import br.com.alura.ResevaSalas.model.Sala;

public record SalaResponseDTO(
        Long id,
        String nome,
        int capacidade,
        boolean ativo,
        String localizacao
) {
    public SalaResponseDTO(Sala sala) {
        this(sala.getId(), sala.getNome(), sala.getCapacidade(), sala.isAtivo(), sala.getLocalizacao());
    }
}
