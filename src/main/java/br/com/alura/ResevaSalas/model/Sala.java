package br.com.alura.ResevaSalas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sala")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private int capacidade;

    private boolean ativo;

    private String localizacao;

    public Sala(long l, String s, int i, boolean b, String blocoA) {
    }

    public Sala() {

    }

    public String getNome() {
        return nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
