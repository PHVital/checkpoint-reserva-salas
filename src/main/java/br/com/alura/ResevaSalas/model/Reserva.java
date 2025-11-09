package br.com.alura.ResevaSalas.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sala sala;

    @ManyToOne
    private Usuario usuario;

    private LocalDateTime inicio;

    private LocalDateTime fim;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    private String motivoCancelamento;

    public Reserva(Sala sala, Usuario usuario, LocalDateTime inicio, LocalDateTime fim, StatusReserva statusReserva) {
    }

    public Reserva() {

    }

    public Reserva(long l, Sala salaAtiva, Usuario usuarioAtivo, LocalDateTime inicio, LocalDateTime fim, StatusReserva statusReserva) {
    }

    public Long getId() {
        return id;
    }

    public Sala getSala() {
        return sala;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }
}
