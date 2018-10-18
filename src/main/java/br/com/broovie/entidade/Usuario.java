package br.com.broovie.entidade;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table
public class Usuario extends EntidadePadrao {
    @Column(length = 70)
    private String nome;

    @Column(length = 30)
    private String nomeUsuario;

    @Column(length = 40)
    private String email;

    @Column
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column
    private String cidade;

    @Column
    private String estado;

    @Column
    private String pais;

    @Column
    private String senha;
}
