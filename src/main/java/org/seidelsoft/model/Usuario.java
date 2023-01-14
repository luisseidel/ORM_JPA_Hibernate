package org.seidelsoft.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name="Usuario.findAll", query="select u from Usuario u"),
    @NamedQuery(name="Usuario.findByName", query="select u from Usuario u where u.nome like :nome")

})
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

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Telefone> telefoneList;

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
