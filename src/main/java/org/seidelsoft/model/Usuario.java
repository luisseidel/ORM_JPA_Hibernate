package org.seidelsoft.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;
    @Column(name = "email", length = 200, nullable = false)
    private String email;
    @Column(name = "login", length = 50, nullable = false)
    private String login;
    @Column(name = "senha", length = 50, nullable = false)
    private String senha;
    @Column(name = "dataNascimento", nullable = false)
    private Calendar dataNascimento;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
