package org.seidelsoft.model;

import lombok.Getter;
import lombok.Setter;
import org.seidelsoft.model.enums.TipoTelefone;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="telefones")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "tipoTelefone", nullable = false)
    private TipoTelefone tipoTelefone;
    @Column(name="numero", nullable = false)
    private String numero;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", tipoTelefone=" + tipoTelefone +
                ", numero='" + numero + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
