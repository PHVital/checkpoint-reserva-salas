package br.com.alura.ResevaSalas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private boolean ativo;

    private String cargo;

    public Usuario(long l, String pedro, String mail, boolean b, String dev) {
    }

    public Usuario() {

    }
}
