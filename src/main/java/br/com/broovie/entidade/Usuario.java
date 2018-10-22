package br.com.broovie.entidade;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

@NamedQueries({
        @NamedQuery(name = "Usuario.findByNomeUsuario", query = "SELECT u FROM Usuario u WHERE u.nomeUsuario = :nomeUsuario"),
        @NamedQuery(name = "Usuario.autenticar", query = "SELECT u FROM Usuario u WHERE u.nomeUsuario = :nomeUsuario AND u.senha = :senha")
})
@Entity
@Table
public class Usuario extends EntidadePadrao {
    @Column(length = 70)
    private String nome;

    @Column(length = 30, unique = true)
    private String nomeUsuario;

    @Column(length = 40)
    private String email;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    /*
       @Column
        private String cidade;

        @Column
        private String estado;
    */
    @Column
    private String pais;

    @Column
    private String senha;
}
