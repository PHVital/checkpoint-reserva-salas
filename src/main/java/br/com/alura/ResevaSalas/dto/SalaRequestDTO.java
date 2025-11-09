package br.com.alura.ResevaSalas.dto;

public record SalaRequestDTO(
        String nome,
        int capacidade,
        boolean ativo,
        String localizacao
) {
}
